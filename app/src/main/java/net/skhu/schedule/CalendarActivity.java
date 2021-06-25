package net.skhu.schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;

import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import net.skhu.notice.NoticeActivity;
import net.skhu.notice.NoticeEditActivity;
import net.skhu.notice.RetrofitService;
import net.skhu.traveler.R;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarActivity extends AppCompatActivity {

    RetrofitService retrofitService = new RetrofitService();
    List<Schedule> schedulelist;

    public TextView select_day_TextView;
    public TextView schedule_textView;

    public Button schedule_save_button;
    public Button schedule_edit_button;
    public Button schedule_delete_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        select_day_TextView = findViewById(R.id.select_day_TextView);
        schedule_textView = findViewById(R.id.schedule_textView);
        schedule_save_button = findViewById(R.id.schedule_save_button);
        schedule_edit_button = findViewById(R.id.schedule_edit_button);
        schedule_delete_button = findViewById(R.id.schedule_delete_button);

        //켈린더 생성 및 초기설정
        MaterialCalendarView calendarView = findViewById(R.id.calendarView);
        //시작시 커서 설정, 주석처리.
//        calendarView.setSelectedDate(CalendarDay.today());

        ScheduleApi scheduleApi = retrofitService.getScheduleRetrofit();
        Call<List<Schedule>> call = scheduleApi.getScheduleList(1);
        call.enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                if(response.isSuccessful()){
                    schedulelist = response.body();
                    for(int i = 0; i<schedulelist.size(); i++) {
                        int year = ((schedulelist.get(i).getStart()) / 10000);
                        int month = (((schedulelist.get(i).getStart()) /100) %100);
                        int dayOfMonth = ((schedulelist.get(i).getStart()) % 100);
                        calendarView.addDecorator(new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(year, month-1, dayOfMonth))));
                    }
                }
                else {
                    Log.d("Test", "실패");
                }
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable t) {

            }
        });

        //달력에서 클릭 시 이벤트
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int year = date.getYear();
                int month = date.getMonth()+1;
                int dayOfMonth = date.getDay();

                //기본 적으로 보이는건 일정 만들기 버튼 1개
                select_day_TextView.setVisibility(View.VISIBLE);
                select_day_TextView.setText(String.format("%d / %d / %d",year, month, dayOfMonth));

                //클릭한 달력 날짜
                int day = Integer.parseInt(year + String.format("%02d",month) + String.format("%02d",dayOfMonth));


                for(int i = 0 ; i < schedulelist.size() ; i++){
                    //클릭한 날짜가 서버에서 받은 일정리스트에 있으면
                    if(day == schedulelist.get(i).getStart()) {
                        //필요한 버튼 보이게 설정
                        select_day_TextView.setText(String.format("%d / %d / %d ~ %d / %d / %d", year, month, dayOfMonth,((schedulelist.get(i).getEnd()) / 10000),
                                (((schedulelist.get(i).getEnd()) /100) %100),((schedulelist.get(i).getEnd()) % 100)));
                        schedule_save_button.setVisibility(View.INVISIBLE);
                        schedule_textView.setVisibility(View.VISIBLE);
                        schedule_textView.setText("장소 : " + schedulelist.get(i).getPlace() + "\n\n인원 : " + schedulelist.get(i).getTotal());
                        schedule_edit_button.setVisibility(View.VISIBLE);
                        schedule_delete_button.setVisibility(View.VISIBLE);

                        //해당 일정의 인덱스를 알기 위한
                        int finalI = i;

                        //수정
                        schedule_edit_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(CalendarActivity.this, SchedulePopup.class);
                                intent.putExtra("button","edit");
                                intent.putExtra("schedule",schedulelist.get(finalI));
                                startActivity(intent);
//                                startActivity(new Intent(CalendarActivity.this, SchedulePopup.class));
                            }
                        });

                        //삭제
                        schedule_delete_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Call<Schedule> call = scheduleApi.deleteSchedule(schedulelist.get(finalI).getIdx());
                                call.enqueue(new Callback<Schedule>() {
                                    @Override
                                    public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                                        if (response.isSuccessful()) {

                                        } else {

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Schedule> call, Throwable t) {

                                    }
                                });
                                startActivity(new Intent(CalendarActivity.this, CalendarActivity.class));
                            }
                        });
                        break;

                    }

                    else {
                        schedule_save_button.setVisibility(View.VISIBLE);
                        schedule_textView.setVisibility(View.INVISIBLE);
                        schedule_edit_button.setVisibility(View.INVISIBLE);
                        schedule_delete_button.setVisibility(View.INVISIBLE);
                    }
                }

            }
        });

        //새 일정
        schedule_save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarActivity.this, SchedulePopup.class);
                intent.putExtra("button","save");
                startActivity(intent);
//                startActivity(new Intent(CalendarActivity.this, ScheduleCreateActivity.class));
            }
        });
    }

    //해당 날짜에 일정이 있으면 시작 날짜에 체크표시 이벤트
    public class EventDecorator implements DayViewDecorator {

        private final int color;
        private final HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates) {
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(5, color));
        }
    }
}