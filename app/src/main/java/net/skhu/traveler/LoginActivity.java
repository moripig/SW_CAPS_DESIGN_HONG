package net.skhu.traveler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.widget.EditText;

import net.skhu.notice.NoticeActivity;

public class LoginActivity extends AppCompatActivity {

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true; }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_date_search) {
            Intent intent = new Intent(this, CalendarActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_map_search) {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_community) {
            Intent intent = new Intent(this, NoticeActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_schedule) {
            Intent intent = new Intent(this, MyScheduleActivity2.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    Button login_btn;
    Button join_btn;
    EditText login_email;
    EditText login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_btn = findViewById(R.id.login_button);
        join_btn = findViewById(R.id.join_button);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = login_email.getText().toString();
                if (isEmptyOrWhiteSpace(email))
                    login_email.setError("아이디를 입력해주세요.");

                String password = login_password.getText().toString();
                if (isEmptyOrWhiteSpace(password))
                    login_password.setError("비밀번호를 입력해주세요.");

                Intent intent1 = new Intent(LoginActivity.this, MapActivity.class);

                if(isEmptyOrWhiteSpace(email) ==false && isEmptyOrWhiteSpace(password) == false)
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
    static boolean isEmptyOrWhiteSpace(String s) {
        if (s == null) return true;
        return s.trim().length() == 0;
    }

}