package net.skhu.traveler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.Menu;

public class LoginActivity extends AppCompatActivity {

    Button login_btn;
    Button join_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_btn = findViewById(R.id.login_button);
        join_btn = findViewById(R.id.join_button);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(LoginActivity.this, MapActivity.class);

                startActivity(intent1);
            }
        });

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(LoginActivity.this, JoinActivity.class);

                startActivity(intent2);
            }
        });
    }

}