package net.skhu.traveler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyScheduleAdapter extends RecyclerView.Adapter<MyScheduleAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder  {
        TextView textView1,textView2,textView3;

        public ViewHolder(View view) {
            super(view);
            textView1 = view.findViewById(R.id.date);
            textView2 = view.findViewById(R.id.destination);
            textView3 = view.findViewById(R.id.number);
        }
        public void setData(){
            Text text = arrayList.get(getAdapterPosition());
            textView1.setText(text.getDateFormatted());
            textView2.setText(text.getTitle());
            textView3.setText(text.getNumber());
        }
    }

    LayoutInflater layoutInflater;
    static ArrayList<Text> arrayList;

    public MyScheduleAdapter(Context context, ArrayList<Text> arrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.textview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int index) {
        viewHolder.setData();
    }
}
