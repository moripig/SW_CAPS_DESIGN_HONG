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
import java.text.SimpleDateFormat;
import java.util.Date;
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

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

//

//


public class NoticeActivity extends AppCompatActivity {
    RetrofitService retrofitService = new RetrofitService();
    Button test_button; //게시판으로 돌아가는 버튼
    Button list_button; //게시판으로 돌아가는 버튼
    Button edit_button;
    Button delete_button;
    Button previous_button;
    Button next_button;

    TextView textView_title;
    TextView textView_body;
    TextView textView_day;
    TextView textView_createDay;
    TextView textView_loca;
    int date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        //목록으로 돌아가는 버튼 설정
        list_button = findViewById(R.id.list_button);
        edit_button = findViewById(R.id.edit_button);
        delete_button = findViewById(R.id.delete_button);
        previous_button = findViewById(R.id.previous_button);
        next_button = findViewById(R.id.next_button);

        textView_title=findViewById(R.id.textView_title);
        textView_body=findViewById(R.id.textView_body);
        textView_day=findViewById(R.id.textView_day);
        textView_createDay=findViewById(R.id.textView_createDay);
        textView_loca=findViewById(R.id.textView_loca);

        //목록에서 클릭 시 ID가져옴
        int id = getIntent().getExtras().getInt("id");

        NoticeApi noticeApi = retrofitService.getRetrofit();

        //글 불러오기
        Call<Notice> call = noticeApi.getPost(id);

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
                    date=notice.getDate();
                }
                else {
                    Log.d("Test", "실패");
                }
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {
                System.out.println("실패");
            }
        });

        //목록으로 돌악가는 버튼
        list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoticeActivity.this, NoticeBoardActivity.class));
            }
        });

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this, NoticeEditActivity.class);
                intent.putExtra("editId",id);
                intent.putExtra("editDate",date);
                startActivity(intent);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NoticeApi noticeApi = retrofitService.getRetrofit();

                //글 불러오기
                Call<Notice> call = noticeApi.deletePost(id);

                call.enqueue(new Callback<Notice>() {
                    @Override
                    public void onResponse(Call<Notice> call, Response<Notice> response) {
                        if (response.isSuccessful()) {
                            System.out.println("삭제완료");
                        } else {
                            Log.d("Test", "실패");
                        }
                    }

                    @Override
                    public void onFailure(Call<Notice> call, Throwable t) {
                        System.out.println("실패");
                    }
                });
                startActivity(new Intent(NoticeActivity.this, NoticeBoardActivity.class));
            }
        });

        //인덱스 차이가 날 수 있는데 +1 은 못하고 리스트 받아와서 그 다음인덱스 거를 해야할듯
//        previous_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(NoticeActivity.this, NoticeEditActivity.class);
//                intent.putExtra("editId",id);
//                intent.putExtra("editDate",date);
//                startActivity(intent);
//            }
//        });
//
//        next_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(NoticeActivity.this, NoticeEditActivity.class);
//                intent.putExtra("editId",id);
//                intent.putExtra("editDate",date);
//                startActivity(intent);
//            }
//        });
    }

}