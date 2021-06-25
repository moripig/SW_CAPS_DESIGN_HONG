package net.skhu.bum;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.skhu.notice.NoticeBoardActivity;
import net.skhu.schedule.CalendarActivity;
import net.skhu.traveler.R;

import java.io.Serializable;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    int  idx;
    List<SeoulInfo> seoulInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        idx = getIntent().getIntExtra("ID",0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_covid_info) {
            Toast.makeText(this, "코로나 현황", Toast.LENGTH_SHORT).show();
            CovidInfo covid = new CovidInfo();
            SeoulLocation location = new SeoulLocation();
            location.start();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            covid.start(location.getSeoulInfo());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            seoulInfoList = covid.getSeoulInfo();
            Intent intent1 = new Intent(LoginActivity.this, SearchActivity.class);
            intent1.putExtra("seoulInfo", (Serializable) seoulInfoList);
            startActivity(intent1);
            return true;
        } else if (id == R.id.menu_board) {
            Toast.makeText(this, "게시판 ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, NoticeBoardActivity.class);
            intent.putExtra("ID", idx);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_shedule) {
            Toast.makeText(this, "내 일정", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CalendarActivity.class);
            intent.putExtra("ID", idx);
            startActivity(intent);
            return true;
        }
            return super.onOptionsItemSelected(item);
    }
}