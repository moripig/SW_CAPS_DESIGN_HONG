package net.skhu.bum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.skhu.traveler.R;

import java.util.List;

/**
 * Created by Administrator on 2017-08-07.
 */

public class SearchAdapter extends BaseAdapter {


    private Context context;
    private List<String> list;
    private List<String> total;
    private List<String> today;
    private LayoutInflater inflate;
    private ViewHolder viewHolder;

    public SearchAdapter(List<String> list, List<String> total, List<String> today, Context context){
        this.list = list;
        this.today = today;
        this.total = total;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflate.inflate(R.layout.search_item,null);

            viewHolder = new ViewHolder();
            viewHolder.label = (TextView) convertView.findViewById(R.id.label);
            viewHolder.total = (TextView) convertView.findViewById(R.id.covid_total);
            viewHolder.today = (TextView) convertView.findViewById(R.id.covid_today);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // 리스트에 있는 데이터를 리스트뷰 셀에 뿌린다.
        viewHolder.label.setText(list.get(position));
        viewHolder.total.setText("총 : "+total.get(position)+"명");
        viewHolder.today.setText("+"+today.get(position)+"명");


        return convertView;
    }

    class ViewHolder{
        public TextView label;
        public TextView total;
        public TextView today;



    }

}