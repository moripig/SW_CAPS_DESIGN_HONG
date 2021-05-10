package net.skhu.notice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.skhu.traveler.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoticeBoardActivity extends AppCompatActivity implements View.OnClickListener {

    Button search_button;   //검색 버튼
    Button create_button;   //새로운 글 작성 버튼
    EditText editText_search;

    //첫화면
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);

        findViewById(R.id.setting_button).setOnClickListener(this); //test

        create_button = findViewById(R.id.create_button);
        search_button = findViewById(R.id.search_butten);
        editText_search = (EditText)findViewById(R.id.editText_search);

        Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("http://10.0.2.2:8088/")
                .baseUrl("http://192.168.0.8:8088/")    //로컬에 연결
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NoticeApi noticeApi = retrofit.create(NoticeApi.class);

        //처음에 전부 가져오기.
        Call<List<Notice>> call = noticeApi.getAll();
        call.enqueue(new Callback<List<Notice>>() {
            @Override
            public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                if(response.isSuccessful()){
                    List<Notice> list = response.body();
                    Log.d("Test", "최초 접속 시 서버에서 가져오는 것들입니다.");
                    Log.d("Test", "ListView를 아직 구현하지 못해 콘솔에서 표시하였고");
                    Log.d("Test", "ListView 구현 후 해당 내용들을 채워 넣은 리스트를 구현할 것입니다.");
                    Log.d("Test","post1 : " + list.get(0).getTitle() + "내용 : " + list.get(0).getBody());
                    Log.d("Test", "post2 : " +list.get(1).getTitle() + "내용 : " + list.get(1).getBody());
                    Log.d("Test","post3 : " + list.get(2).getTitle() + "내용 : " + list.get(2).getBody());
//                    Notice notice = response.body();
//                    textView_title.setText(notice.getTitle());
//                    textView_body.setText(notice.getBody());
//                    textView_loca.setText("장소 : " + notice.getLoca());
//                    textView_createDay.setText("작성일 : " + Integer.toString(notice.getDate()));
//                    textView_day.setText("기간 : " + Integer.toString(notice.getStart()) + " ~ " + Integer.toString(notice.getEnd()));
                }
                else {
                    Log.d("Test", "실패");
                }
            }

            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {

            }
        });




        //생성
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
                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("http://10.0.2.2:8088/")
                        .baseUrl("http://192.168.0.8:8088/")    //로컬에 연결
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                NoticeApi noticeApi = retrofit.create(NoticeApi.class);
                System.out.println(editText_search.getText().toString());
                Call<List<Notice>> call = noticeApi.getTitle(editText_search.getText().toString());

                call.enqueue(new Callback<List<Notice>>() {
                    @Override
                    public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                        if(response.isSuccessful()){
                            List<Notice> list = response.body();
                            Log.d("Test",list.get(0).getTitle());
                            Log.d("Test","성공");
                        }
                        else {
                            Log.d("Test", "실패1");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Notice>> call, Throwable t) {
                        System.out.println("오류남");
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