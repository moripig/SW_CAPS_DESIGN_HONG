package net.skhu.traveler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    Button intent_btn1;
    Button intent_btn2;
    Button intent_btn3;
    Button intent_btn4;

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        intent_btn1 = findViewById(R.id.intent_btn1);
        intent_btn2 = findViewById(R.id.intent_btn2);
        intent_btn3 = findViewById(R.id.intent_btn3);
        intent_btn4 = findViewById(R.id.intent_btn4);

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

                Intent intent3 = new Intent(MainActivity.this, LoginActivity.class);

                startActivity(intent3);
            }
        });

    }
}