package net.skhu.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import net.skhu.traveler.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeEditActivity extends AppCompatActivity {
    RetrofitService retrofitService = new RetrofitService();

    Button cancel_button;  //취소하고 게시판으로 돌아가는 버튼
    Button edit_notice_button; //생성버튼

    Button start_button;    //시작일 작성버튼
    Button end_button;      //종료일 작성버튼

    EditText editText_title;
    EditText editText_body;
    EditText editText_member;
    EditText editText_loca;

    int startday;
    int endday;

    //달력
    private DatePickerDialog.OnDateSetListener callbackMethodStart;
    private DatePickerDialog.OnDateSetListener callbackMethodEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_edit);

        //수정할 게시글의 id와 작성일 가져옴
        int id = getIntent().getExtras().getInt("editId");
        int date = getIntent().getExtras().getInt("editDate");

        //매칭
        editText_title = (EditText)findViewById(R.id.editText_title);
        editText_body = (EditText)findViewById(R.id.editText_body);
        editText_member = (EditText)findViewById(R.id.editText_member);
        editText_loca = (EditText)findViewById(R.id.editText_loca);
        edit_notice_button = findViewById(R.id.edit_notice_button);

        editText_title.setText(getIntent().getExtras().getString("editTitle"));
        editText_body.setText(getIntent().getExtras().getString("editBody"));
        editText_loca.setText(getIntent().getExtras().getString("editLoca"));
        editText_member.setText(Integer.toString(getIntent().getExtras().getInt("editMember")));

        //달력
        this.InitializeView();
        this.InitializeListener();

        //수정 버튼 클릭 시 데이터 전송
        edit_notice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//                Date d = new Date();
//                int today = Integer.parseInt(format.format(d));

                NoticeApi noticeApi = retrofitService.getRetrofit();

                //사용자가 입력한 정보 Notice 객체 만들기
                String title = editText_title.getText().toString();
                String body = editText_body.getText().toString();
                int start = getStartday();
                int end = getEndday();
                String loca= editText_loca.getText().toString();
                int member = Integer.parseInt(editText_member.getText().toString());
                int hit = 0;
                String cate= "미정";
                Notice notice = new Notice(
                        id,
                        title,
                        body,
                        start,
                        end,
                        loca,
                        member,
                        date,
                        hit,
                        cate
                );

                Call<Notice> call = noticeApi.editPost(notice);

                call.enqueue(new Callback<Notice>() {
                    @Override
                    public void onResponse(Call<Notice> call, Response<Notice> response) {
                        if(response.isSuccessful()){
                            Log.d("Test", call.toString());
                            Log.d("Test", "확인");
                        }
                    }

                    @Override
                    public void onFailure(Call<Notice> call, Throwable t) {

                    }
                });

                startActivity(new Intent(NoticeEditActivity.this, NoticeBoardActivity.class));
            }
        });

        //취소버튼 클릭 시
        cancel_button = findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoticeEditActivity.this, NoticeBoardActivity.class));
            }
        });
        

    }
    //달력
    public void InitializeView()
    {
        start_button = findViewById(R.id.start_button);
        end_button = findViewById(R.id.end_button);
        start_button.setText(Integer.toString(getIntent().getExtras().getInt("editStart")));
        end_button.setText(Integer.toString(getIntent().getExtras().getInt("editStart")));
    }

    public void InitializeListener()
    {
        callbackMethodStart = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                monthOfYear += 1;
                start_button.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
                setStartday(Integer.parseInt(Integer.toString(year) + String.format("%02d",monthOfYear) + String.format("%02d",dayOfMonth)));
            }
        };
        callbackMethodEnd = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                monthOfYear += 1;
                end_button.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
                setEndday(Integer.parseInt(Integer.toString(year) + String.format("%02d",monthOfYear) + String.format("%02d",dayOfMonth)));
            }
        };
    }

    public void OnClickHandler(View view)
    {
        if(view.getId() == R.id.start_button) {
            DatePickerDialog dialog = new DatePickerDialog(this, callbackMethodStart, 2021, 2, 14);


            dialog.show();
        } else if(view.getId() == R.id.end_button) {
            DatePickerDialog dialog = new DatePickerDialog(this, callbackMethodEnd, 2021, 2, 14);


            dialog.show();
        }
    }
    public void setStartday(int startday) {
        this.startday = startday;
    }

    public void setEndday(int endday) {
        this.endday = endday;
    }

    public int getStartday() {
        return startday;
    }

    public int getEndday() {
        return endday;
    }
}