package net.skhu.traveler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;


import net.skhu.notice.NoticeActivity;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private ISessionCallback mSessionCallback;



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

        mSessionCallback = new ISessionCallback() {
            @Override
            public void onSessionOpened() {
                //로그인 요청
                UserManagement.getInstance().me(new MeV2ResponseCallback() {
                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        //로그인 실패했을 때
                        Toast.makeText(LoginActivity.this, "로그인 도중에 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        // 세션이 닫혔을 때
                        Toast.makeText(LoginActivity.this, "세션이 닫혔습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(MeV2Response result) {
                        //로그인 성공했을 때
                        Intent intent = new Intent(LoginActivity.this, MyProfileActivity.class);
                        intent.putExtra("name", result.getKakaoAccount().getProfile().getNickname());
                        intent.putExtra("profileImg", result.getKakaoAccount().getProfile().getProfileImageUrl());
                        intent.putExtra("email", result.getKakaoAccount().getEmail());
                        intent.putExtra("sex", result.getKakaoAccount().getGender());
                        intent.putExtra("age", result.getKakaoAccount().getAgeRange());
                        startActivity(intent);

                        Toast.makeText(LoginActivity.this, "로그인 성공! 환영합니다.", Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @Override
            public void onSessionOpenFailed(KakaoException exception) {
                Toast.makeText(LoginActivity.this, "onSessionOpenFailed", Toast.LENGTH_SHORT).show();
            }
        };
        Session.getCurrentSession().addCallback(mSessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();

        login_btn = findViewById(R.id.login_button);
        join_btn = findViewById(R.id.join_button);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email.getText().toString();
                String password = login_password.getText().toString();

                if (isEmptyOrWhiteSpace(email))
                    login_email.setError("아이디를 입력해주세요.");


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
                Toast.makeText(LoginActivity.this, "회원가입 페이지로 이동합니다", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(LoginActivity.this, JoinActivity.class);

                startActivity(intent2);
            }
        });
    }
    static boolean isEmptyOrWhiteSpace(String s) {
        if (s == null) return true;
        return s.trim().length() == 0;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(Session.getCurrentSession().handleActivityResult(requestCode,resultCode,data))
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(mSessionCallback);
    }

}