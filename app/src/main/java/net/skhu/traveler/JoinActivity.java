package net.skhu.traveler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.Menu;

public class JoinActivity extends AppCompatActivity {

    Button check_btn;
    Button join_btn;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        check_btn = findViewById(R.id.check_button);
        join_btn = findViewById(R.id.join_button);
        delete = findViewById(R.id.delete);

        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(JoinActivity.this, JoinActivity.class);

                startActivity(intent1);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(JoinActivity.this, LoginActivity.class);

                startActivity(intent2);
            }
        });

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent3 = new Intent(JoinActivity.this, LoginActivity.class);

                startActivity(intent3);
            }
        });
    }
}