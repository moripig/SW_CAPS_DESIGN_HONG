package net.skhu.bum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.skhu.traveler.R;

import java.io.Serializable;
import java.util.List;


public class BumMainActivity extends AppCompatActivity{

    List<SeoulInfo> seoulInfoList;
    Button search_btn;                 // 회원가입 버튼
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bum_main);

        search_btn = (Button)findViewById(R.id.go_covidInfo);

        btn_login = (Button)findViewById(R.id.go_login1);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(BumMainActivity.this, Login2Activity.class);

                startActivity(intent1);
            }
        });
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
    }

}