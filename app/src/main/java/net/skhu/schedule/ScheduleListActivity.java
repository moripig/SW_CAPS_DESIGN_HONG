// 사용 X -> CalendarActivity로 대처하였음
//
// 1. 화면에 들어오면 먼저 서버랑 연동해서 List를 생성
// 2. List에 있는 것들을 해당 날짜에 정보를 넣어야한다.
// 2-1. retrofit단계에서 바로 집어넣기?
// 2-2. save메서드를 만들어서 날짜+장소+인원수 보낸 뒤? TextView채워넣기? -> 공유하는거라 불가능
// 2-3. save메서드를 만들면 -> 새로 일정 만들 때 사용가능한가?
// 2-3-1. 사용자에게 전부 입력 받은 뒤 확정버튼 클릭하면, 서버에 데이터보내면 다시 엑티비티화면 돌아올 때 저절로할듯
// 2-4. 날짜에 접근을 어떻게하지???
// 3. 날짜 클릭 시 이미 데이터가 있으면 수정 삭제버튼, 없으면 새로만들기 버튼
// 3-1. 확인은 어떻게? textview가 비어있으면? or 처음 textview채워 넣을 때 해당 날짜 VISIBLE변경하기?
// 4. 마지막으로 textview에 뭐 있으면 해당 날짜 달력에 표시하기
// 결론 -> TextView는 공유하는거라 정보 못넣음, 기본 캘린더 뷰에서는 표시방법이 적절하지 않아 커스텀 뷰 사용.


//package net.skhu.schedule;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CalendarView;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import net.skhu.notice.Notice;
//import net.skhu.notice.NoticeActivity;
//import net.skhu.notice.NoticeApi;
//import net.skhu.notice.NoticeBoardActivity;
//import net.skhu.notice.RetrofitService;
//import net.skhu.traveler.R;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//
//
//
//public class ScheduleListActivity extends AppCompatActivity {
//
//    RetrofitService retrofitService = new RetrofitService();
//    List<Schedule> schedulelist;
//
//    public String fname=null;
//    public String str=null;
//    public CalendarView calendarView;
//    public Button cha_Btn,del_Btn,save_Btn;
//    public TextView diaryTextView,textView2,textView3;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_schedule_list);
//        calendarView=findViewById(R.id.calendarView);
//        diaryTextView=findViewById(R.id.diaryTextView);
//        save_Btn=findViewById(R.id.save_Btn);
//        del_Btn=findViewById(R.id.del_Btn);
//        cha_Btn=findViewById(R.id.cha_Btn);
//        textView2=findViewById(R.id.textView2);
//        textView3=findViewById(R.id.textView3);
//
//        ScheduleApi scheduleApi = retrofitService.getScheduleRetrofit();
//        Call<List<Schedule>> call = scheduleApi.getScheduleList(1);
//        call.enqueue(new Callback<List<Schedule>>() {
//            @Override
//            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
//                if(response.isSuccessful()){
//                    schedulelist = response.body();
//                    //이제 이 리스트를 달력에 저장하고싶은데 해당 날짜로 어떻게 접근해야할지
//                }
//                else {
//                    Log.d("Test", "실패");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Schedule>> call, Throwable t) {
//
//            }
//        });
//
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                diaryTextView.setVisibility(View.VISIBLE);
//                diaryTextView.setText(String.format("%d / %d / %d",year,month+1,dayOfMonth));
//
//
//                int day = Integer.parseInt(year + String.format("%02d",month+1) + String.format("%02d",dayOfMonth));
//
//                for(int i = 0; i<schedulelist.size(); i++){
//                    if(day == schedulelist.get(i).getStart()) {
//                        save_Btn.setVisibility(View.INVISIBLE);
//                        textView2.setVisibility(View.VISIBLE);
//                        textView2.setText(schedulelist.get(i).getPlace() + "여행");
//                        cha_Btn.setVisibility(View.VISIBLE);
//                        del_Btn.setVisibility(View.VISIBLE);
//
//                        //실험
//                        int finalI = i;
//                        del_Btn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Call<Schedule> call = scheduleApi.deleteSchedule(schedulelist.get(finalI).getIdx());
//                                call.enqueue(new Callback<Schedule>() {
//                                    @Override
//                                    public void onResponse(Call<Schedule> call, Response<Schedule> response) {
//                                        if (response.isSuccessful()) {
//
//                                        } else {
//
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<Schedule> call, Throwable t) {
//
//                                    }
//                                });
//                              startActivity(new Intent(ScheduleListActivity.this, ScheduleListActivity.class));
//                            }
//                        });
//                        break;
//                    }
//
//                    else {
//                        save_Btn.setVisibility(View.VISIBLE);
//                        textView2.setVisibility(View.INVISIBLE);
//                        cha_Btn.setVisibility(View.INVISIBLE);
//                        del_Btn.setVisibility(View.INVISIBLE);
//                        //checkDay(year,month,dayOfMonth);
//                    }
//                }
//
//            }
//        });
//        //save버튼 클릭 시
//        save_Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ScheduleListActivity.this, ScheduleCreateActivity.class));
////                saveDiary(fname);
////                textView2.setText(str);
////                save_Btn.setVisibility(View.INVISIBLE);
////                cha_Btn.setVisibility(View.VISIBLE);
////                del_Btn.setVisibility(View.VISIBLE);
////                textView2.setVisibility(View.VISIBLE);
//
//            }
//        });
//
//
//
//    }
//
//    public void  checkDay(int cYear,int cMonth,int cDay){
//
//            textView2.setVisibility(View.VISIBLE);
////            textView2.setText(str);
//
//            save_Btn.setVisibility(View.VISIBLE);
//            cha_Btn.setVisibility(View.INVISIBLE);
//
//
//            cha_Btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    textView2.setVisibility(View.INVISIBLE);
////                    save_Btn.setVisibility(View.VISIBLE);
////                    cha_Btn.setVisibility(View.INVISIBLE);
////                    del_Btn.setVisibility(View.INVISIBLE);
//                }
//
//            });
//
//
//            if(textView2.getText()==null){
////                textView2.setVisibility(View.INVISIBLE);
////                diaryTextView.setVisibility(View.VISIBLE);
////                save_Btn.setVisibility(View.VISIBLE);
////                cha_Btn.setVisibility(View.INVISIBLE);
////                del_Btn.setVisibility(View.INVISIBLE);
//
//            }
//
////        }catch (Exception e){
////            e.printStackTrace();
////        }
//    }
//
//    //textView2에 채워넣기 + 뷰 보이게 하는거 설정하기.
//    public void saveDiary(Schedule schedule){
////                save_Btn.setVisibility(View.VISIBLE);
////                textView2.setVisibility(View.INVISIBLE);
////                cha_Btn.setVisibility(View.INVISIBLE);
////                del_Btn.setVisibility(View.INVISIBLE);
////                diaryTextView.setText(String.format("%d / %d / %d",year,month+1,dayOfMonth));
////                checkDay(year,month,dayOfMonth);
//            }
//}