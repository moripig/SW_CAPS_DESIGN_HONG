package net.skhu.notice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.skhu.traveler.R;

//
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonArray;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.Queue;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.util.LinkedList; //import
import java.util.Queue; //import

//

//


public class NoticeActivity extends AppCompatActivity {
    Button test_button; //게시판으로 돌아가는 버튼
    Button list_button; //게시판으로 돌아가는 버튼
    TextView textView_title;
    TextView textView_body;
    TextView textView_day;
    TextView textView_createDay;
    TextView textView_loca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        //목록으로 돌아가는 버튼 설정
        list_button = findViewById(R.id.list_button);
        textView_title=findViewById(R.id.textView_title);
        textView_body=findViewById(R.id.textView_body);
        textView_day=findViewById(R.id.textView_day);
        textView_createDay=findViewById(R.id.textView_createDay);
        textView_loca=findViewById(R.id.textView_loca);


        //목록으로 돌악가는 버튼
        list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoticeActivity.this, NoticeBoardActivity.class));
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("http://10.0.2.2:8088/")
                .baseUrl("http://192.168.0.8:8088/")    //로컬에 연결
                .addConverterFactory(GsonConverterFactory.create())
                 .build();

        NoticeApi noticeApi = retrofit.create(NoticeApi.class);

        //유저가 리스트에서 게시글 클릭 시 id가 채워지도록 구현 예정
        Call<Notice> call = noticeApi.getPost(100);

        call.enqueue(new Callback<Notice>() {
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                if(response.isSuccessful()){
                    Notice notice = response.body();
                    textView_title.setText(notice.getTitle());
                    textView_body.setText(notice.getBody());
                    textView_loca.setText("장소 : " + notice.getLoca());
                    textView_createDay.setText("작성일 : " + Integer.toString(notice.getDate()));
                    textView_day.setText("기간 : " + Integer.toString(notice.getStart()) + " ~ " + Integer.toString(notice.getEnd()));
                }
                else {
                    Log.d("Test", "실패");
                }
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {

            }
        });

//        //API 확인 용 버튼 누를 시임.
//        test_button = findViewById(R.id.test_button);
//        test_button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Retrofit retrofit = new Retrofit.Builder()
////                        .baseUrl("http://10.0.2.2:8088/")
//                        .baseUrl("http://192.168.0.8:8088/")
////                        .baseUrl(("https://jsonplaceholder.typicode.com"))
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//
//                NoticeApi noticeApi = retrofit.create(NoticeApi.class);
//
//                noticeApi.getDatas(100).enqueue((new Callback<List<Notice>>() {
//                    @Override
//                    public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
//                        List<Notice> data = response.body();
//                        System.out.println("성공인가?");
//                        Log.d("TEST", "성공임");
//                        Log.d("Test",data.get(0).getTitle());
//                        Log.d("Test",data.get(0).getBody());
//                        Log.d("Test",data.get(1).getTitle());
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Notice>> call, Throwable t) {
//                        t.printStackTrace();
//                    }
//                }
//                ));
//                System.out.println(noticeApi);
//
//
//            }
//        }); //여기까지가 API테스트용
    }

}