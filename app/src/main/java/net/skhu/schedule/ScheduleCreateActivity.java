package net.skhu.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.skhu.notice.Notice;
import net.skhu.notice.NoticeApi;
import net.skhu.notice.NoticeBoardActivity;
import net.skhu.notice.RetrofitService;
import net.skhu.notice.TemporaryNoticeActivity;
import net.skhu.traveler.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleCreateActivity extends AppCompatActivity {

    RetrofitService retrofitService = new RetrofitService();

    EditText newschedule_start;
    EditText newschedule_end;
    EditText newschedule_total;
    EditText newschedule_where;

    Button newschedule_createbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_create);

        newschedule_start = (EditText)findViewById(R.id.newschedule_start);
        newschedule_end = (EditText)findViewById(R.id.newschedule_end);
        newschedule_total = (EditText)findViewById(R.id.newschedule_total);
        newschedule_where = (EditText)findViewById(R.id.newschedule_where);

        newschedule_createbutton = findViewById(R.id.newschedule_createbutton);
        newschedule_createbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ScheduleApi scheduleApi = retrofitService.getScheduleRetrofit();

                Schedule schedule = new Schedule(
                        0,
                        newschedule_where.getText().toString(),
                        Integer.parseInt(newschedule_start.getText().toString()),
                        Integer.parseInt(newschedule_end.getText().toString()),
                        Integer.parseInt(newschedule_total.getText().toString()),
                        1
                );

                Call<Schedule> call = scheduleApi.setSchedule(schedule);
                call.enqueue(new Callback<Schedule>() {
                    @Override
                    public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                        if(response.isSuccessful()){
                            Log.d("Test", call.toString());
                            Log.d("Test", "확인");
                        }
                    }

                    @Override
                    public void onFailure(Call<Schedule> call, Throwable t) {

                    }
                });

                startActivity(new Intent(ScheduleCreateActivity.this, ScheduleListActivity.class));
            }
        });
    }
}