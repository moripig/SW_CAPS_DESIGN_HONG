package net.skhu.schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import net.skhu.notice.RetrofitService;
import net.skhu.traveler.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleListActivity extends AppCompatActivity {

    RetrofitService retrofitService = new RetrofitService();

    public String fname=null;
    public String str=null;
    public CalendarView calendarView;
    public Button cha_Btn,del_Btn,save_Btn;
    public TextView diaryTextView,textView2,textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);
        calendarView=findViewById(R.id.calendarView);
        diaryTextView=findViewById(R.id.diaryTextView);
        save_Btn=findViewById(R.id.save_Btn);
        del_Btn=findViewById(R.id.del_Btn);
        cha_Btn=findViewById(R.id.cha_Btn);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        //연결부분임 건드릴 필요 없을듯

        //서버에서 정보 받아오기

        ScheduleApi scheduleApi = retrofitService.getScheduleRetrofit();
        Call<Schedule> call = scheduleApi.getScheduleList(1);
        call.enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                if(response.isSuccessful()){
                    Schedule schedule = response.body();
                    System.out.println(response.body().getIdx());
                    System.out.println(response.body().getUserid());
                    System.out.println(response.body().getWhere());
                    System.out.println(response.body().getStart());
                    System.out.println(response.body().getEnd());
                    System.out.println(response.body().getTotal());

//                    textView_title.setText(title);
//                    textView_body.setText(body);
//                    textView_loca.setText("장소 : " + loca);
//                    textView_member.setText("인원 : " + member);
//                    textView_createDay.setText("작성일 : " + date);
//                    textView_day.setText("기간 : " + start + " ~ " + end);

                }
                else {
                    Log.d("Test", "실패");
                }
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {
                System.out.println("실패");
            }
        });
        
        //로그인 및 회원가입 엑티비티에서 이름을 받아옴

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //날짜 클릭 시 버튼 어떤거 보여줄 지
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                diaryTextView.setVisibility(View.VISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                diaryTextView.setText(String.format("%d / %d / %d",year,month+1,dayOfMonth));
                //checkDay(year,month,dayOfMonth);
            }
        });
        //나중에 새로운 일정 생성 버튼으로 하기.
        save_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDiary(fname);
                textView2.setText(str);
                save_Btn.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.VISIBLE);
                del_Btn.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);

            }
        });
    }

    public void  checkDay(int cYear,int cMonth,int cDay){

            textView2.setVisibility(View.VISIBLE);
//            textView2.setText(str);

            save_Btn.setVisibility(View.INVISIBLE);
            cha_Btn.setVisibility(View.VISIBLE);
            del_Btn.setVisibility(View.VISIBLE);

            cha_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    textView2.setVisibility(View.INVISIBLE);
//                    save_Btn.setVisibility(View.VISIBLE);
//                    cha_Btn.setVisibility(View.INVISIBLE);
//                    del_Btn.setVisibility(View.INVISIBLE);
                }

            });
            del_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    textView2.setVisibility(View.INVISIBLE);
//                    save_Btn.setVisibility(View.VISIBLE);
//                    cha_Btn.setVisibility(View.INVISIBLE);
//                    del_Btn.setVisibility(View.INVISIBLE);
//                    removeDiary(fname);
                }
            });

            if(textView2.getText()==null){
//                textView2.setVisibility(View.INVISIBLE);
//                diaryTextView.setVisibility(View.VISIBLE);
//                save_Btn.setVisibility(View.VISIBLE);
//                cha_Btn.setVisibility(View.INVISIBLE);
//                del_Btn.setVisibility(View.INVISIBLE);

            }

//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
    @SuppressLint("WrongConstant")
    public void removeDiary(String readDay){

    }
    @SuppressLint("WrongConstant")
    public void saveDiary(String readDay){

    }


//    //데이터 가져오는 것 + 달력 만들기 전임, 달력 수틀리면 다시 여기로
//    RetrofitService retrofitService = new RetrofitService();
//    TextView test_idx;
//    TextView test_where;
//    TextView test_start;
//    TextView test_end;
//    TextView test_total;
//    TextView test_userid;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_schedule_list);
//
//        test_idx = findViewById(R.id.test_idx);
//        test_where = findViewById(R.id.test_where);
//        test_start = findViewById(R.id.test_start);
//        test_end = findViewById(R.id.test_end);
//        test_total = findViewById(R.id.test_total);
//        test_userid = findViewById(R.id.test_userid);
//
//        ScheduleApi scheduleApi = retrofitService.getScheduleRetrofit();
//
//        Call<Schedule> call = scheduleApi.getScheduleList(1);
//
//        call.enqueue(new Callback<Schedule>() {
//            @Override
//            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
//                if(response.isSuccessful()){
//                    Schedule schedule = response.body();
//                    System.out.println(response.body().getIdx());
//                    System.out.println(response.body().getUserid());
//                    System.out.println(response.body().getWhere());
//                    System.out.println(response.body().getStart());
//                    System.out.println(response.body().getEnd());
//                    System.out.println(response.body().getTotal());
//
////                    textView_title.setText(title);
////                    textView_body.setText(body);
////                    textView_loca.setText("장소 : " + loca);
////                    textView_member.setText("인원 : " + member);
////                    textView_createDay.setText("작성일 : " + date);
////                    textView_day.setText("기간 : " + start + " ~ " + end);
//
//                }
//                else {
//                    Log.d("Test", "실패");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Schedule> call, Throwable t) {
//                System.out.println("실패");
//            }
//        });
//    }
}