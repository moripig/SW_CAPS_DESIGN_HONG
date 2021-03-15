package net.skhu.traveler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button intent_btn1;
    Button intent_btn2;
    Button intent_btn3;
    Button intent_btn4;
    Button intent_btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent_btn1 = findViewById(R.id.intent_btn1);
        intent_btn2 = findViewById(R.id.intent_btn2);
        intent_btn3 = findViewById(R.id.checkReservation);
        intent_btn4 = findViewById(R.id.SearchList);
        intent_btn5 = findViewById(R.id.mySchedule);

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

                Intent intent1 = new Intent(MainActivity.this, CheckReservationActivity.class);

                startActivity(intent1);
            }
        });
        intent_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(MainActivity.this, SearchListActivity2.class);

                startActivity(intent1);
            }
        });
        intent_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(MainActivity.this, MyScheduleActivity2.class);

                startActivity(intent1);
            }
        });
    }
}