package net.skhu.traveler;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.Menu;

import net.skhu.notice.NoticeActivity;
import net.skhu.notice.NoticeBoardActivity;
import net.skhu.notice.TemporaryNoticeActivity;


public class MainActivity extends AppCompatActivity {

    Button intent_btn1;
    Button intent_btn2;
    Button intent_btn3;
    Button intent_btn4;
    Button intent_btn5;
    Button intent_btn6;
    Button intent_btn7;
    Button intent_btn8;
    Button intent_btn9;

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true; }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent_btn1 = findViewById(R.id.Calendar);
        intent_btn2 = findViewById(R.id.Map);
        intent_btn3 = findViewById(R.id.checkReservation);
        intent_btn4 = findViewById(R.id.SearchList);
        intent_btn5 = findViewById(R.id.mySchedule);
        intent_btn6 = findViewById(R.id.intent_Login);
        intent_btn7 = findViewById(R.id.Notice);
        intent_btn8 = findViewById(R.id.myProfile);
        intent_btn9 = findViewById(R.id.Map2);



        intent_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(MainActivity.this, CalendarActivity.class);

                startActivity(intent1);
            }
        });

        intent_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(MainActivity.this, MapActivity.class);

                startActivity(intent2);
            }
        });

        intent_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent3 = new Intent(MainActivity.this, CheckReservationActivity.class);

                startActivity(intent3);
            }
        });

        intent_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MainActivity.this, SearchListActivity2.class);

                startActivity(intent4);
            }
        });

//        intent_btn5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent5 = new Intent(MainActivity.this, MyScheduleActivity2.class);
//
//                startActivity(intent5);
//            }
//        });

        intent_btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent6 = new Intent(MainActivity.this, LoginActivity.class);

                startActivity(intent6);
            }
        });

        intent_btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent7 = new Intent(MainActivity.this, NoticeActivity.class);

                startActivity(intent7);
            }
        });

        intent_btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent8 = new Intent(MainActivity.this, MyProfileActivity.class);

                startActivity(intent8);
            }
        });

        intent_btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent9 = new Intent(MainActivity.this, MapActivity2.class);

                startActivity(intent9);
            }
        });

    }
}