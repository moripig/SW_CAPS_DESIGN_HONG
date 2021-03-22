package net.skhu.notice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import net.skhu.traveler.R;


public class TemporaryNoticeActivity extends AppCompatActivity implements View.OnClickListener{

    Button cancel_button;  //취소하고 게시판으로 돌아가는 버튼
    private TextView textView_Date;         //test
    private DatePickerDialog.OnDateSetListener callbackMethod;  //test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_temporary);

        findViewById(R.id.create_notice_nutton).setOnClickListener(this); //팝업

        this.InitializeView();  //test
        this.InitializeListener();  //test


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
            case R.id.create_notice_nutton:
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