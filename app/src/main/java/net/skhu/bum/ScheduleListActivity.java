package net.skhu.bum;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.skhu.traveler.R;

import java.util.ArrayList;
import java.util.ListIterator;

public class ScheduleListActivity extends AppCompatActivity {
    int idx;
    ScheduleListAdapter scheduleListAdapter;
    ArrayList<Schedule> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scehdule_list);
        idx = getIntent().getIntExtra("loginId",0);

        arrayList = new ArrayList<Schedule>();

        scheduleListAdapter = new ScheduleListAdapter(this,arrayList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.scheduleRecyclerVIew);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(scheduleListAdapter);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_schedule_list,menu);
        MenuItem menuItem = menu.findItem(R.id.action_remove);
        menuItem.setVisible(scheduleListAdapter.checkedCount>0);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_create) {
            Intent intent = new Intent(this, MyScheduleActivity.class);
            intent.putExtra("loginId",idx);
            startActivityForResult(intent,0);
        }
        else if(id == R.id.action_remove){
            deleteItems();
         return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            Schedule schedule = (Schedule) intent.getSerializableExtra("SCHEDULE");
            arrayList.add(schedule);
            scheduleListAdapter.notifyDataSetChanged();
        }
    }


    private void deleteItems() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirm);
        builder.setMessage(R.string.doYouWantToDelete);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ListIterator<Schedule> iterator = arrayList.listIterator();
                while (iterator.hasNext())
                    if(iterator.next().isChecked())
                        iterator.remove();
                    scheduleListAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton(R.string.no,null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}