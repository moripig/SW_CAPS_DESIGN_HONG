package net.skhu.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.skhu.traveler.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView comment_writer;
        TextView comment_date;
        TextView comment_body;

        public ViewHolder(View view) {
            super(view);
            comment_writer = view.findViewById(R.id.comment_writer);
            comment_date = view.findViewById(R.id.comment_date);
            comment_body = view.findViewById(R.id.comment_body);
            //view.setOnClickListener(this); 클릭시임 댓글에서 필요 x
        }
        public void setData(int index) {
            Comment comment = arrayList.get(index);
            comment_writer.setText("작성자 : " + comment.getWriter());
            comment_body.setText(comment.getBody());
            comment_date.setText("작성일" + comment.getDate());
        }

        //onClick 구현 필요없을듯
    }

    ArrayList<Comment> arrayList;
    LayoutInflater layoutInflater;

    public CommentAdapter(Context context, ArrayList<Comment> arrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.comment, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int index) {
        viewHolder.setData(index);

        //id넘길 때임
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onCli
//        });
    }
}
