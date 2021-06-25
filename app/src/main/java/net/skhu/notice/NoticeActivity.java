package net.skhu.notice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.skhu.traveler.R;

//
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonArray;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class NoticeActivity extends AppCompatActivity {
    RetrofitService retrofitService = new RetrofitService();

    Button list_button;
    Button edit_button;
    Button delete_button;
    Button comment_button;

    TextView textView_title;
    TextView textView_body;
    TextView textView_day;
    TextView textView_createDay;
    TextView textView_loca;
    TextView textView_member;

    EditText editText_create;

    int id;
    int date;
    String title;
    String body;
    String loca;
    int member;
    int start;
    int end;
    int userid;

    boolean own=false;

    Notice notice;

    List<Comment> list = new ArrayList<>();
    CommentAdapter commentAdapter; // 리사이클 뷰 어뎁터
    ArrayList<Comment> arrayList; // 채워 넣을 곳

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        list_button = findViewById(R.id.list_button);
        edit_button = findViewById(R.id.edit_button);
        delete_button = findViewById(R.id.delete_button);
        comment_button = findViewById(R.id.comment_button);

        textView_title=findViewById(R.id.textView_title);
        textView_body=findViewById(R.id.textView_body);
        textView_day=findViewById(R.id.textView_day);
        textView_createDay=findViewById(R.id.textView_createDay);
        textView_loca=findViewById(R.id.textView_loca);
        textView_member=findViewById(R.id.textView_member);

        editText_create = (EditText)findViewById(R.id.editText_create);

        //해당 글 Idx
        id = getIntent().getExtras().getInt("id");

        Intent intent = getIntent();
        //현재 접속 한 userid임
//        userid = (int) intent.getSerializableExtra("ID");
        userid = getIntent().getExtras().getInt("ID");

        NoticeApi noticeApi = retrofitService.getRetrofit();

        //글 내용 가져오기
        Call<Notice> call = noticeApi.getPost(id);
        call.enqueue(new Callback<Notice>() {
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                if(response.isSuccessful()){
                    notice = response.body();

                    textView_title.setText(notice.getTitle());
                    textView_body.setText(notice.getBody());
                    textView_loca.setText("장소 : " + notice.getLoca());
                    textView_member.setText("인원 : " + notice.getMember());
                    textView_createDay.setText("작성일 : " + notice.getDate());
                    textView_day.setText("기간 : " + notice.getStart() + " ~ " + notice.getEnd());

                    if(userid == notice.getWriteridx()) {
                        own = true;
                        edit_button.setVisibility(View.VISIBLE);
                        delete_button.setVisibility(View.VISIBLE);
                    }
                    else {
                        edit_button.setVisibility(View.GONE);
                        delete_button.setVisibility(View.GONE);
                    }

                }
                else {
                    Log.d("Test", "실패");
                }
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {

            }
        });

        //댓글 가져오기
        Call<List<Comment>> call2 = noticeApi.getComment(id);
        call2.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(response.isSuccessful()){
                    if(response.body().size() !=0) {

                        list = response.body();

                        arrayList = new ArrayList<Comment>();
                        for(int i=0; i < list.size(); i++) {
                            arrayList.add((list.get(i)));
                    }

                    commentAdapter = new CommentAdapter(getApplicationContext(), arrayList);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.comment_list);
                    recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setItemAnimator((new DefaultItemAnimator()));
                    recyclerView.setAdapter(commentAdapter);
                    }
                    else {

                    }

                }
                else {

                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });

        //목록으로 돌아가기
        list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this, NoticeBoardActivity.class);
                intent.putExtra("ID",userid);
                startActivity(intent);
//                startActivity(new Intent(NoticeActivity.this, NoticeBoardActivity.class));
            }
        });

        //수정화면으로
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this, NoticeEditActivity.class);
                //나중에 객체로 전송하기
                intent.putExtra("editId",notice.getId());
                intent.putExtra("editDate", notice.getDate());
                intent.putExtra("editTitle", notice.getTitle());
                intent.putExtra("editBody", notice.getBody());
                intent.putExtra("editLoca", notice.getLoca());
                intent.putExtra("editMember", notice.getMember());
                intent.putExtra("editStart", notice.getStart());
                intent.putExtra("editEnd", notice.getEnd());
                intent.putExtra("ID", userid);
                startActivity(intent);
            }
        });

        //삭제
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

                        } else {
                            Log.d("Test", "실패");
                        }
                    }

                    @Override
                    public void onFailure(Call<Notice> call, Throwable t) {

                    }
                });
                Intent intent = new Intent(NoticeActivity.this, NoticeBoardActivity.class);
                intent.putExtra("ID",userid);
                startActivity(intent);
//                startActivity(new Intent(NoticeActivity.this, NoticeBoardActivity.class));
            }
        });

        //댓글 생성
        comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                NoticeApi noticeApi = retrofitService.getRetrofit();

                Call<Comment> call = noticeApi.setComment(new Comment(0,editText_create.getText().toString(),Integer.parseInt(format.format(new Date())), id, userid));
                call.enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> response) {
                        if (response.isSuccessful()) {

                        } else {
                            Log.d("Test", "실패");
                        }
                    }

                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {

                    }
                });
                Intent intent = new Intent(NoticeActivity.this, NoticeActivity.class);
                intent.putExtra("ID",userid);
                intent.putExtra("id", id);
                startActivity(intent);

//                startActivity(new Intent(NoticeActivity.this, NoticeBoardActivity.class));
            }
        });


        //이전, 다음글 버튼, 구현x
//        //인덱스 차이가 날 수 있는데 +1 은 못하고 리스트 순서대로? or DB저장방식을 변경?
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