package net.skhu.notice;

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


import com.lakue.pagingbutton.LakuePagingButton;
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
    RetrofitService retrofitService = new RetrofitService();    //객체 가져오기 위함

    Button search_button;   //검색 버튼
    Button create_button;   //새로운 글 작성 버튼
    EditText editText_search;


    NoticeListAdapter noticeListAdapter; // 리사이클 뷰 어뎁터
    ArrayList<Notice> arrayList; // 채워 넣을 곳

    List<Notice> list = new ArrayList<>();

    //첫화면
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);

        findViewById(R.id.setting_button).setOnClickListener(this); //test

        create_button = findViewById(R.id.create_button);
        search_button = findViewById(R.id.search_butten);
        editText_search = (EditText)findViewById(R.id.editText_search);


        //목록 불러오기
        NoticeApi noticeApi = retrofitService.getRetrofit();

        Call<List<Notice>> call = noticeApi.getAll();

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

        //새로운글 작성하기 넘어가는것
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoticeBoardActivity.this, TemporaryNoticeActivity.class));
            }
        });

        //검색
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoticeApi noticeApi = retrofitService.getRetrofit();
                System.out.println(editText_search.getText().toString());
//                Call<List<Notice>> call = noticeApi.gettest();
                Call<List<Notice>> call = noticeApi.getTitle(editText_search.getText().toString());

                call.enqueue(new Callback<List<Notice>>() {
                    @Override
                    public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                        if(response.isSuccessful()){
                            if(response.body().size() != 0) {
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
                                //검색결과 없는 것으로 화면 바꿔주기. 어떻게 해야함?
                                //구현 못할거 같으면 그냥 if문 삭제해
                                System.out.println("검색결과 없음");
                            }
                        }
                        else {
                            Log.d("Test", "실패");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Notice>> call, Throwable t) {

                    }
                });
//                startActivity(new Intent(NoticeBoardActivity.this, NoticeActivity.class));
            }
        });

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_button:
                new AlertDialog.Builder(this)
                        .setTitle("날짜 및 여행지 세팅")
                        .setMessage("날짜 : \n장소 :")
                        .setNeutralButton("저장", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                            }
                        })
                        .show(); // 팝업창 보여줌
                break;
        }
    }

}