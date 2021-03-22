package net.skhu.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.skhu.traveler.R;

public class NoticeActivity extends AppCompatActivity {

    Button list_button; //게시판으로 돌아가는 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        list_button = findViewById(R.id.list_button);
        list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoticeActivity.this, NoticeBoardActivity.class));
            }
        });

    }


}