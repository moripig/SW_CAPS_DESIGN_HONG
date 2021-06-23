package net.skhu.bum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.skhu.traveler.R;

import java.io.Serializable;
import java.util.List;


public class BumMainActivity extends AppCompatActivity implements View.OnClickListener{

    List<SeoulInfo> seoulInfoList;
    Button search_btn;                 // 회원가입 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bum_main);

        search_btn = (Button)findViewById(R.id.go_search);


        search_btn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_search:     // 회원가입 버튼
                Intent intent = new Intent(BumMainActivity.this, SearchActivity.class);
                intent.putExtra("seoulInfo", (Serializable) seoulInfoList);
                startActivity(intent);
                break;

            case R.id.go_login1:
                Intent intent2 = new Intent(BumMainActivity.this, MyScheduleActivity.class);
                startActivity(intent2);
                break;
            case R.id.go_covidInfo:
                CovidInfo covid = new CovidInfo();
                SeoulLocation location = new SeoulLocation();
                location.start();
                try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                covid.start(location.getSeoulInfo());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                seoulInfoList = covid.getSeoulInfo();
                Intent intent1 = new Intent(BumMainActivity.this, SearchActivity.class);
                intent1.putExtra("seoulInfo", (Serializable) seoulInfoList);
                startActivity(intent1);
                break;

        }

    }
}