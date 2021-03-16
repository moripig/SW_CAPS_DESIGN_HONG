package net.skhu.traveler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;


public class SearchListActivity extends AppCompatActivity {

    SearchListAdapter searchListAdapter;
    ArrayList<List> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        arrayList = new ArrayList<List>();
        arrayList.add(new List("부산 해운대","부산 해운대구 우동"));
        arrayList.add(new List("서울 해운대", "서울특별시 마포구 마포동"));

        searchListAdapter = new SearchListAdapter(this, arrayList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listRecyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(searchListAdapter);
    }
}
