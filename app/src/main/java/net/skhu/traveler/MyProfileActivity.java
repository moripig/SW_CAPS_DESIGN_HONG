package net.skhu.traveler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import net.skhu.notice.NoticeActivity;

public class MyProfileActivity extends AppCompatActivity {

    Button btn_logout;
    private String strNick, strProfileImg, strEmail;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Intent intent = getIntent();
        strNick = intent.getStringExtra("name");
        strProfileImg = intent.getStringExtra("profileImg");
        strEmail = intent.getStringExtra("email");

        TextView kakao_nick = findViewById(R.id.kakao_nickname);
        TextView kakao_email = findViewById(R.id.kakao_email);
        ImageView kakao_profile = findViewById(R.id.kakao_profile);


        kakao_nick.setText(strNick);
        kakao_email.setText(strEmail);


        Glide.with(this).load(strProfileImg).into(kakao_profile);


        // 로그아웃

        btn_logout = findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        // 로그아웃 성공시 수행하는 시점
                        finish();
                    }
                });
            }
        });



    }


}