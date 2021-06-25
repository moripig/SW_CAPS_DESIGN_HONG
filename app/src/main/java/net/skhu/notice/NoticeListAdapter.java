package net.skhu.notice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import net.skhu.traveler.MainActivity;
import net.skhu.traveler.R;
import net.skhu.traveler.SearchListAdapter;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class NoticeListAdapter extends RecyclerView.Adapter<NoticeListAdapter.ViewHolder> {

    class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemTitle;
        TextView itemWriter;
        TextView itemDate;
        TextView itemDay;


        //어느 택스트를 넣을 지?
        public ViewHolder(View view) {
            super(view);
            itemTitle = view.findViewById(R.id.item_title);
            itemWriter = view.findViewById(R.id.item_writer);
            itemDate = view.findViewById(R.id.item_date);
            itemDay = view.findViewById(R.id.item_day);
            view.setOnClickListener(this);
        }
        public  void setData() {
            Notice notice = arrayList.get(getAdapterPosition());
            itemTitle.setText(notice.getTitle());
            itemWriter.setText("작성자 : " + Integer.toString(notice.getId()));
            itemDate.setText("작성일 : " + Integer.toString(notice.getDate()));
            itemDay.setText("여행기간 : " + Integer.toString(notice.getStart())+ " ~ " + Integer.toString(notice.getEnd()));

        }



        @Override
        public void onClick(View view) {
            Notice notice = arrayList.get(super.getAdapterPosition());
            String s = String.format("index: %d, title: %s", super.getAdapterPosition(), notice.getTitle());
            Toast.makeText(view.getContext(), s, Toast.LENGTH_SHORT).show();
        }
    }

    ArrayList<Notice> arrayList;
    LayoutInflater layoutInflater;
    int userid;
    public NoticeListAdapter (Context context, ArrayList<Notice> arrayList, int userid) {
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
        this.userid = userid;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.noticeitem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int index) {
        viewHolder.setData();

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoticeActivity.class);
                intent.putExtra("id", arrayList.get(index).getId());
                intent.putExtra("ID", userid);
                v.getContext().startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));


            }
        });
    }

//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int index) {
//        ViewHolder myViewHolder = (ViewHolder) viewHolder;
//        myViewHolder.textView.setText(arrayList.get(index));
//    }
}
