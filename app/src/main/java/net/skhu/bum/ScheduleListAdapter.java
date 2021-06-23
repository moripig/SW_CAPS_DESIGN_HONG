package net.skhu.bum;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.skhu.traveler.R;

import java.util.ArrayList;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        TextView scheduleId, destination, start, end, howMany;
        CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            scheduleId = view.findViewById(R.id.scheduleId);
            start = view.findViewById(R.id.startDate);
            end = view.findViewById(R.id.endDate);
            destination = view.findViewById(R.id.destination);
            howMany = view.findViewById(R.id.howMany);
        }


        public void setData() {
           Schedule schedule = arrayList.get(getAdapterPosition());
            scheduleId.setText(schedule.getId());
            start.setText(schedule.getDateFormattedStart());
            end.setText(schedule.getDateFormattedEnd());
            destination.setText(schedule.getDestination());
            howMany.setText(schedule.getNumber());

        }


        @Override
        public void onClick(View view) {
           Schedule schedule = arrayList.get(super.getAdapterPosition());
            //

        }



        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
           Schedule schedule = arrayList.get(super.getAdapterPosition());
            schedule.setChecked(isChecked);
            if (isChecked) ++checkedCount;
            else --checkedCount;
            if (isChecked && checkedCount == 1 || !isChecked && checkedCount == 0) {
                Activity activity = (Activity) scheduleId.getContext();
                activity.invalidateOptionsMenu();
            }
        }
    }
    LayoutInflater layoutInflater;
    ArrayList<Schedule> arrayList;
    int checkedCount = 0;

    public ScheduleListAdapter(Context context, ArrayList<Schedule> arrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.schedule_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.setData();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}