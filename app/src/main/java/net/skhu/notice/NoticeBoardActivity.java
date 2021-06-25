package net.skhu.notice;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.lakue.pagingbutton.OnPageSelectListener;

import net.skhu.traveler.MainActivity;
import net.skhu.traveler.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoticeBoardActivity extends AppCompatActivity implements View.OnClickListener {
    RetrofitService retrofitService = new RetrofitService();

    Button search_button;
    Button create_button;
    EditText editText_search;

    Button previous_button;
    Button next_button;

    // 리사이클 뷰
    NoticeListAdapter noticeListAdapter;
    ArrayList<Notice> arrayList;
    List<Notice> list = new ArrayList<>();

    int pagenumber=0;

    //첫화면
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        findViewById(R.id.setting_button).setOnClickListener(this);

        create_button = findViewById(R.id.create_button);
        search_button = findViewById(R.id.search_butten);
        editText_search = (EditText)findViewById(R.id.editText_search);
        previous_button = findViewById(R.id.previous_button);
        next_button = findViewById(R.id.next_button);

        //서버에서 목록 가져옴
        getPageList(pagenumber);

        //새로운글 작성화면으로
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoticeBoardActivity.this, TemporaryNoticeActivity.class));
            }
        });

        //검색(제목)
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagenumber = 0;
                getPageList(pagenumber);
            }
        });

        //다음페이지
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagenumber += 1;
                getPageList(pagenumber);
            }
        });

        //이전페이지
        previous_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagenumber -= 1;
                getPageList(pagenumber);
            }
        });

    }

    //목록 불러오기
    public void getPageList(int page) {

        NoticeApi noticeApi = retrofitService.getRetrofit();

        Call<List<Notice>> call = noticeApi.getPage(page);

        //검색기능, 검색어가 있을 시 다른 통신 시도
        if(!editText_search.getText().toString().toString().equals("")) {
            call = noticeApi.getTitle(editText_search.getText().toString(), pagenumber);
        }

        call.enqueue(new Callback<List<Notice>>() {
            @Override
            public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                if(response.isSuccessful()){
                    list = response.body();

                    arrayList = new ArrayList<Notice>(); //리사이클 뷰
                    for(int i=0; i < list.size(); i++) {
                        arrayList.add((list.get(i)));
                    }
                    noticeListAdapter = new NoticeListAdapter(getApplicationContext(), arrayList);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.notice_list);
                    recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setItemAnimator((new DefaultItemAnimator()));
                    recyclerView.setAdapter(noticeListAdapter);
                }
                else {
                    Log.d("Test", "실패");
                }
            }

            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {

            }
        });
    }

    //다시 화면 시작
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_button:
                startActivity(new Intent(NoticeBoardActivity.this, NoticeBoardActivity.class));
        }
    }

}