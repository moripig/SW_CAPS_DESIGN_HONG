package net.skhu.bum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import net.skhu.traveler.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyScheduleActivity extends AppCompatActivity {
    int idx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);
        idx = getIntent().getIntExtra("loginId",0);
        Button saveBtn = (Button) findViewById(R.id.btnSave);
        Button CancelBtn = (Button) findViewById(R.id.btnCancel);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat trans = new SimpleDateFormat("yyyy년 MM월 dd일");
                EditText editText_start = (EditText) findViewById(R.id.editTextStart);
                String start = editText_start.getText().toString();

                if (isEmptyOrWhiteSpace(start)) {
                    editText_start.setError("시작날짜을 입력하세요");
                    return;
                }
                EditText editText_end = (EditText) findViewById(R.id.editTextEnd);
                String end = editText_start.getText().toString();
                if (isEmptyOrWhiteSpace(end)) {
                    editText_end.setError("끝날짜을 입력하세요");
                    return;
                }

                Button btnDestination = (Button) findViewById(R.id.btnDestination);
                String destination = "용산구";
                EditText editText_howMany = (EditText) findViewById(R.id.editTextHowMany);
                String number = editText_howMany.getText().toString();
                if (isEmptyOrWhiteSpace(number)) {
                    editText_howMany.setError("인원 수를 입력하세요");
                    return;
                }
                Date startDate = null;
                Date endDate = null;
                try {
                    startDate = trans.parse(start);
                    endDate = trans.parse(end);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
               Schedule schedule = new Schedule(idx,10,startDate,endDate, Integer.parseInt(number),destination);
                Intent intent = new Intent();
                intent.putExtra("SCHEDULE", schedule);
                setResult(RESULT_OK, intent);
                finish();
            }
        };
        saveBtn.setOnClickListener(listener);
    }

    static boolean isEmptyOrWhiteSpace(String s) {
        if (s == null) return true;
        return s.toString().trim().length() == 0;
    }
}
