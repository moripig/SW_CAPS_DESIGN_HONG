package net.skhu.traveler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;

public class MyScheduleActivity extends AppCompatActivity {

    MyScheduleAdapter recyclerView2Adapter;
    ArrayList<Text> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        arrayList = new ArrayList<Text>();
        arrayList.add(new Text(new Date(),"해운대","5명"));
        arrayList.add(new Text(new Date(),"창덕궁","2명"));

        recyclerView2Adapter = new MyScheduleAdapter(this, arrayList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerVIew);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerView2Adapter);

    }
}
