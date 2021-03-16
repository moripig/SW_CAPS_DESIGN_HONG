package net.skhu.traveler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoticeBoardActivity extends AppCompatActivity implements View.OnClickListener {

    Button search_button;
    Button create_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);

        findViewById(R.id.setting_button).setOnClickListener(this); //test


        create_button = findViewById(R.id.create_button);
        search_button = findViewById(R.id.search_butten);

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoticeBoardActivity.this, TemporaryNoticeActivity.class));
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoticeBoardActivity.this, NoticeActivity.class));
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