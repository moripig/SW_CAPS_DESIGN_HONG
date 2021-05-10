package net.skhu.notice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import net.skhu.traveler.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TemporaryNoticeActivity extends AppCompatActivity implements View.OnClickListener{

    Button cancel_button;  //취소하고 게시판으로 돌아가는 버튼
    Button create_notice_button; //생성버튼

    EditText editText_title;
    EditText editText_body;

    private TextView textView_Date;         //test
    private DatePickerDialog.OnDateSetListener callbackMethod;  //test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_temporary);

        findViewById(R.id.create_notice_button).setOnClickListener(this); //팝업
        editText_title = (EditText)findViewById(R.id.editText_title);
        editText_body = (EditText)findViewById(R.id.editText_body);


        this.InitializeView();  //test
        this.InitializeListener();  //test

        //제목 + 내용 입력
        editText_title.getText().toString();
        editText_body.getText().toString();



        create_notice_button = findViewById(R.id.create_notice_button);
        create_notice_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("작성 버튼 눌렀음");
                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("http://10.0.2.2:8088/")
                        .baseUrl("http://192.168.0.8:8088/")    //로컬에 연결
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                NoticeApi noticeApi = retrofit.create(NoticeApi.class);

                Notice notice = new Notice(
                        1000,
                        "안드에서 만들기 제목",
                        "내용입니다.",
                        20210510,
                        20210515,
                        "장소는 서울",
                        4,
                        20210520,
                        4,
                        "카테고리"
                );

                Call<Notice> call = noticeApi.setPost(notice);



                call.enqueue(new Callback<Notice>() {
                    @Override
                    public void onResponse(Call<Notice> call, Response<Notice> response) {
                        if(!response.isSuccessful()){
                            Log.d("Test", "실패");
                        }
                        Notice noticeResponse = response.body();
                        String content = "";
                        content += "Code : " + response.code() + "\n";
                        content += "Id : " + noticeResponse.getId() + "\n";
                        content += "Title : " + noticeResponse.getTitle() + "\n";


                    }

                    @Override
                    public void onFailure(Call<Notice> call, Throwable t) {

                    }
                });
                System.out.println("코드 끝");
                //startActivity(new Intent(TemporaryNoticeActivity.this, NoticeBoardActivity.class));
            }
        });


        cancel_button = findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TemporaryNoticeActivity.this, NoticeBoardActivity.class));
            }
        });
    }

    //팝업
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_notice_button:
                new AlertDialog.Builder(this)
                        .setTitle("글 작성")
                        .setMessage("작성 완료")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                            }
                        })
                        .show();
                break;
        }
    }

    //달력
    public void InitializeView()
    {
        textView_Date = (TextView)findViewById(R.id.textView_date);
    }

    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                monthOfYear += 1;
                textView_Date.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
            }
        };
    }
    //달력
    public void OnClickHandler(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2021, 2, 14);

        dialog.show();
    }
}