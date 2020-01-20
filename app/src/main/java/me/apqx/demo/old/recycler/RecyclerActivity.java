package me.apqx.demo.old.recycler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

import me.apqx.demo.R;

public class RecyclerActivity extends Activity {
    private RecyclerView rv_stag;
    private List<String> list;
    private StagAdapter stagAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_2);
        rv_stag = findViewById(R.id.rv_stag);
        list = new ArrayList<>();
        stagAdapter = new StagAdapter(list);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        rv_stag.setAdapter(stagAdapter);
        rv_stag.setLayoutManager(flexboxLayoutManager);

        list.add("测试1");
        list.add("测试2");
        list.add("测试3");
        list.add("测试测试1");
        list.add("测试测试测试1");
        list.add("测试1");
        list.add("测试1");
        list.add("测试1");
        list.add("测试1");
        stagAdapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                break;
            case R.id.btn_del:
                break;
        }
    }
}
