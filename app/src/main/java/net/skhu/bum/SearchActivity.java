package net.skhu.bum;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.skhu.notice.NoticeBoardActivity;
import net.skhu.schedule.CalendarActivity;
import net.skhu.traveler.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class SearchActivity extends AppCompatActivity {

    private List<String> list;
    private List<String> total;
    private List<String> today;// 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private List<SeoulInfo> arrayList;
    private List<SeoulInfo> seoulInfoList;
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        editSearch = (EditText) findViewById(R.id.search_destination);
        listView = (ListView) findViewById(R.id.list_search);


        // 리스트를 생성한다.
        list = new ArrayList<String>();
        total = new ArrayList<String>();
        today = new ArrayList<String>();
        arrayList = (List<SeoulInfo>) getIntent().getSerializableExtra("seoulInfo");
        userid = getIntent().getIntExtra("ID",0);

        // 검색에 사용할 데이터을 미리 저장한다.

        for(int i = 0; i< arrayList.size(); i++) {
            list.add(arrayList.get(i).getNameGu());
            total.add(arrayList.get(i).getTotal());
            today.add(arrayList.get(i).getToday());
        }


        // 리스트에 연동될 아답터를 생성한다.
        adapter = new SearchAdapter(list,total,today, this);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String data = (String) adapterView.getItemAtPosition(position);
                SeoulInfo clickedItem = arrayList.get(position);
                Intent intent1 = new Intent(SearchActivity.this, CalendarActivity.class);
                intent1.putExtra("destination",arrayList.get(position).getNameGu());
            }
        });

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });


    }

    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            for(int i = 0; i< arrayList.size(); i++)
            list.add(arrayList.get(i).nameGu);
        }
        // 문자 입력을 할때..
        else {
            // 리스트의 모든 데이터를 검색한다.
            for (int i = 0; i < arrayList.size(); i++) {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arrayList.get(i).getNameGu().toLowerCase().contains(charText)) {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arrayList.get(i).getNameGu());
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_covid_info) {
            Toast.makeText(this, "코로나 현황", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(SearchActivity.this, SearchActivity.class);
            intent1.putExtra("seoulInfo", (Serializable) arrayList);
            startActivity(intent1);
            return true;
        } else if (id == R.id.menu_board) {
            Toast.makeText(this, "게시판 ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, NoticeBoardActivity.class);
            intent.putExtra("ID", userid);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_shedule) {
            Toast.makeText(this, "내 일정", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CalendarActivity.class);
            intent.putExtra("ID", userid);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}