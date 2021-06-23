package net.skhu.bum;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.skhu.traveler.R;

public class LoginActivity extends AppCompatActivity {
    int idx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        idx = getIntent().getIntExtra("loginId",0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_home) {
            Toast.makeText(this, "홈 메뉴 클릭", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_board) {
            Toast.makeText(this, "게시판 메뉴 클릭", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_shedule) {
            Toast.makeText(this, "내 일정 메뉴 클릭", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ScheduleListActivitye.class);
            intent.putExtra("loginId",idx);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_logout) {
            Toast.makeText(this, "로그아웃 메뉴 클릭", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_status) {
            Toast.makeText(this, "내 상태 메뉴 클릭", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}