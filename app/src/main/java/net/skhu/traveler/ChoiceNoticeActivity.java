package net.skhu.traveler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiceNoticeActivity extends AppCompatActivity {

    Button list_butten; //게시판으로 돌아가는 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_notice);

        list_butten = findViewById(R.id.list_button);
        list_butten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoiceNoticeActivity.this, NoticeActivity.class));
            }
        });

    }


}