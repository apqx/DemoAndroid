package me.apqx.demo.widget.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.apqx.demo.R;

public class ListActivity extends Activity {
    private ListView listView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.lv_top);
        recyclerView = findViewById(R.id.rv_bottom);

        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Student(i, String.valueOf(i)));
        }

        ListAdapter listAdapter = new ListAdapter(this, R.layout.item_list, list);
        listView.setAdapter(listAdapter);

        listAdapter.notifyDataSetChanged();


        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(R.layout.item_list, list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClickListener(student -> {
            // item被点击时回调
        });

        // 通知所有数据刷新
        recyclerAdapter.notifyDataSetChanged();
        // 通知指定item数据刷新
        recyclerAdapter.notifyItemChanged(10);
        // 通知指定范围的item数据刷新
        recyclerAdapter.notifyItemRangeChanged(0, 2);


    }
}
