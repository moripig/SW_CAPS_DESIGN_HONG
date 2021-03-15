package net.skhu.traveler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CreateNoticeActivity extends AppCompatActivity implements View.OnClickListener{

    Button cancel_button;  //취소하고 게시판으로 돌아가는 버튼


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notice);

        findViewById(R.id.create_notice_nutton).setOnClickListener(this); //test


        cancel_button = findViewById(R.id.cancel_button);

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CreateNoticeActivity.this, NoticeActivity.class));
            }
        });
    }

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
                        .show(); // 팝업창 보여줌
                break;
        }
    }
}