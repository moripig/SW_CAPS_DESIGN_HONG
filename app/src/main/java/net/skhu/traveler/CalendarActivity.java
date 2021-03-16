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

public class CalendarActivity extends AppCompatActivity {


    private TextView textViewLocation;
    private TextView textView_DateStart;
    private TextView textView_DateEnd;
    private DatePickerDialog.OnDateSetListener callbackMethod;

    private String[] items = {"1", "2", "3", "4", "5"};


    private TextView textView_Date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


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

        textView_DateStart = (TextView)findViewById(R.id.textView_dateStart);
        textView_DateEnd = (TextView)findViewById(R.id.textView_dateEnd);
        textView_Date = (TextView)findViewById(R.id.textView_date);

    }

    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                monthOfYear += 1;
                textView_DateStart.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
                textView_DateEnd.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
                textView_Date.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
            }
        };
    }

    public void OnClickHandler(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2021, 2, 14);

        dialog.show();
    }
}