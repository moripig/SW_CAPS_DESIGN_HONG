package net.skhu.schedule;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import net.skhu.notice.NoticeBoardActivity;
import net.skhu.notice.RetrofitService;
import net.skhu.notice.TemporaryNoticeActivity;
import net.skhu.traveler.R;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchedulePopup extends Activity {
    RetrofitService retrofitService = new RetrofitService();

    TextView popschedule_start;


    EditText editText_schedule_total;
    EditText editText_schedule_place;

    Button button_schedule_cancel;

    Schedule schedule;
    LinearLayout start_layout;

    //시작, 종료 날짜 선택
    Button button_schedule_end;
    Button button_schedule_start;

    //달력
    private DatePickerDialog.OnDateSetListener callbackMethodStart;
    private DatePickerDialog.OnDateSetListener callbackMethodEnd;
    int startday;
    int endday;
    String buttoType;
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.activity_schedule_popup);

        //일정 받아오기
        Intent intent = getIntent();
        schedule = (Schedule) intent.getSerializableExtra("schedule");
        //어느 버튼으로 들어왔는지 판별하기 위한
        buttoType = (String) intent.getSerializableExtra("button");
        userid = (int) intent.getSerializableExtra("ID");

        button_schedule_start = findViewById(R.id.button_schedule_start);
        button_schedule_end = findViewById(R.id.button_schedule_end);

        //달력
        this.InitializeListener();

        popschedule_start = findViewById(R.id.popschedule_start);
        editText_schedule_total = (EditText)findViewById(R.id.editText_schedule_total);
        editText_schedule_place = (EditText)findViewById(R.id.editText_schedule_place);
        button_schedule_cancel = findViewById(R.id.button_schedule_cancel);
        start_layout = findViewById(R.id.start_layout);


        //새로운 일정이면 기본 화면으로 보여주기
        if(buttoType.equals("save")) {
            popschedule_start.setText("새 일정 만들기");
        }
        //수정버튼이면 해당 일정을 초기값으로 세팅
        else if(buttoType.equals("edit")) {
            popschedule_start.setText("일정 수정하기");
            start_layout.setVisibility(View.GONE);

            editText_schedule_total.setText(Integer.toString(schedule.getTotal()));
            editText_schedule_place.setText(schedule.getPlace());
            button_schedule_start.setText(Integer.toString(schedule.getStart()));
            button_schedule_end.setText(Integer.toString(schedule.getEnd()));
            setStartday(schedule.getStart());
            setEndday(schedule.getEnd());
        }

        //취소버튼
        button_schedule_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //확인 클릭 시
    //둘 다 서버에서 save쓰는데 합칠 수 있는가?? 보류
    public void mOnClose(View v){
        //새로운 일정 서버에 전송
        if(buttoType.equals("save")) {
            ScheduleApi scheduleApi = retrofitService.getScheduleRetrofit();

            Schedule newSchedule = new Schedule(
                    0,
                    editText_schedule_place.getText().toString(),
                    getStartday(),
                    getEndday(),
                    Integer.parseInt(editText_schedule_total.getText().toString()),
                    userid
            );
            Call<Schedule> call = scheduleApi.setSchedule(newSchedule);
            call.enqueue(new Callback<Schedule>() {
                @Override
                public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                    if(response.isSuccessful()){

                    }
                }

                @Override
                public void onFailure(Call<Schedule> call, Throwable t) {

                }
            });
            Intent intent = new Intent(SchedulePopup.this, CalendarActivity.class);
            intent.putExtra("ID", userid);
            startActivity(intent);
//            startActivity(new Intent(SchedulePopup.this, CalendarActivity.class));
        }
        //수정한 것 서버에 전송
        else if(buttoType.equals("edit")) {
            schedule.setEnd(getStartday());
            schedule.setEnd(getEndday());
            schedule.setTotal(Integer.parseInt(editText_schedule_total.getText().toString()));
            schedule.setPlace(editText_schedule_place.getText().toString());

            ScheduleApi scheduleApi = retrofitService.getScheduleRetrofit();
            Call<Schedule> call = scheduleApi.editSchedule(schedule);
            call.enqueue(new Callback<Schedule>() {
                @Override
                public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                    if (response.isSuccessful()) {

                    } else {
                        Log.d("Test", "실패");
                    }
                }

                @Override
                public void onFailure(Call<Schedule> call, Throwable t) {

                }
            });
            Intent intent = new Intent(SchedulePopup.this, CalendarActivity.class);
            intent.putExtra("ID", userid);
            startActivity(intent);
//            startActivity(new Intent(SchedulePopup.this, CalendarActivity.class));
        }
    }

    //달력
    public void InitializeListener()
    {
        callbackMethodStart = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                monthOfYear += 1;
                button_schedule_start.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
                setStartday(Integer.parseInt(Integer.toString(year) + String.format("%02d",monthOfYear) + String.format("%02d",dayOfMonth)));
            }
        };
        callbackMethodEnd = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                monthOfYear += 1;
                button_schedule_end.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
                setEndday(Integer.parseInt(Integer.toString(year) + String.format("%02d",monthOfYear) + String.format("%02d",dayOfMonth)));
            }
        };
    }

    public void OnClickHandler(View view)
    {
        if(view.getId() == R.id.button_schedule_start) {
            DatePickerDialog dialog = new DatePickerDialog(this, callbackMethodStart, 2021, 2, 14);
            dialog.show();
        } else if(view.getId() == R.id.button_schedule_end) {
            DatePickerDialog dialog = new DatePickerDialog(this, callbackMethodEnd, 2021, 2, 14);
            dialog.show();
        }
    }

    public void setStartday(int startday) {
        this.startday = startday;
    }

    public void setEndday(int endday) {
        this.endday = endday;
    }

    public int getStartday() {
        return startday;
    }

    public int getEndday() {
        return endday;
    }


    //바깥 클릭해도 팝업 유지
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    //안드로이드 백버튼 막기
    @Override
    public void onBackPressed() {
        return;
    }

}