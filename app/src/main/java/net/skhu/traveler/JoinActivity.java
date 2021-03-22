package net.skhu.traveler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.widget.EditText;

public class JoinActivity extends AppCompatActivity {

    Button check_btn;
    Button join_btn;
    Button cancel;
    EditText join_name;
    EditText join_email;
    EditText join_password;
    EditText join_pwck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        check_btn = findViewById(R.id.check_button);
        join_btn = findViewById(R.id.join_button);
        cancel = findViewById(R.id.cancel);

        join_name = findViewById(R.id.join_name);
        join_email = findViewById(R.id.join_email);
        join_password = findViewById(R.id.join_password);
        join_pwck = findViewById(R.id.join_pwck);

        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(JoinActivity.this, JoinActivity.class);

                startActivity(intent1);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(JoinActivity.this, LoginActivity.class);

                startActivity(intent2);
            }
        });

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = join_name.getText().toString();
                if (isEmptyOrWhiteSpace(name))
                    join_name.setError("이름을 입력해주세요.");

                String email = join_email.getText().toString();
                if (isEmptyOrWhiteSpace(email))
                    join_email.setError("이메일을 입력해주세요.");

                String password = join_password.getText().toString();
                if (isEmptyOrWhiteSpace(password))
                    join_password.setError("비밀번호를 입력해주세요.");

                String pwck = join_pwck.getText().toString();
                if (password.equals(pwck) == false)
                    join_pwck.setError("비밀번호 일치여부를 확인해주세요.");

                Intent intent3 = new Intent(JoinActivity.this, LoginActivity.class);

                if(isEmptyOrWhiteSpace(name) == false && isEmptyOrWhiteSpace(email) == false &&
                        isEmptyOrWhiteSpace(password) == false && password.equals(pwck) == true)
                    startActivity(intent3);
            }
        });
    }
    static boolean isEmptyOrWhiteSpace(String s) {
        if (s == null) return true;
        return s.trim().length() == 0;
    }

}