package net.skhu.traveler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialCalendar;

public class CalendarActivity extends AppCompatActivity {


    private TextView textViewLocation;
    private TextView textView_dateStart;
    private TextView textView_dateEnd;
    private DatePickerDialog.OnDateSetListener callbackMethodStart;
    private DatePickerDialog.OnDateSetListener callbackMethodEnd;

    private String[] items = {"1명", "2명", "3명", "4명", "5명"};


   // private TextView textView_Date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //MaterialCalendar materialCalendarView = findViewById(R.id.calendarView);
        //materialCalendarView.setOnDateChangedListener(this);

        Spinner spinner = findViewById(R.id.spinner);
        textViewLocation = findViewById(R.id.textViewLocation);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        this.InitializeView();
        this.InitializeListener();
    }

    public void InitializeView()
    {

        textView_dateStart = (TextView)findViewById(R.id.textView_dateStart);
        textView_dateEnd = (TextView)findViewById(R.id.textView_dateEnd);
        //textView_Date = (TextView)findViewById(R.id.textView_date);

    }

    public void InitializeListener()
    {
        callbackMethodStart = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                monthOfYear += 1;
                textView_dateStart.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
                //textView_Date.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
            }
        };
        callbackMethodEnd = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                monthOfYear += 1;
                textView_dateEnd.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
                //textView_Date.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
            }
        };
    }

    public void OnClickHandler(View view)
    {
        if(view.getId() == R.id.buttonStart) {
            DatePickerDialog dialog = new DatePickerDialog(this, callbackMethodStart, 2021, 2, 14);


            dialog.show();
        } else if(view.getId() == R.id.buttonEnd) {
            DatePickerDialog dialog = new DatePickerDialog(this, callbackMethodEnd, 2021, 2, 14);


            dialog.show();
        }
    }
}