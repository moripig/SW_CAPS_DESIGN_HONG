package net.skhu.traveler;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder>{

    class ViewHolder extends RecyclerView.ViewHolder  {
        TextView textView1, textView2;

        public ViewHolder(View view) {
            super(view);
            textView1 = view.findViewById(R.id.listDest);
            textView2 = view.findViewById(R.id.listAddress);
        }

        public void setData() {
            List list = arrayList.get(getAdapterPosition());
            textView1.setText(list.getDestination());
            textView2.setText(list.getAddress());
        }
    }
    LayoutInflater layoutInflater;
    ArrayList<List> arrayList;

    public  SearchListAdapter(Context context, ArrayList<List> arrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int index) {
        viewHolder.setData();
    }


}
