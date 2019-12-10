package me.apqx.demo.widget.view;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.apqx.demo.LogUtil;
import me.apqx.demo.R;

public class RelativeActivity extends Activity {
    private RecyclerView rv_temp;
    private CusAdapter adapter;
    private List<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_relative);
        rv_temp = findViewById(R.id.rv_temp);

        list = new ArrayList<>();
        adapter = new CusAdapter(list);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv_temp.setAdapter(adapter);
        rv_temp.setLayoutManager(layoutManager);

        View in = findViewById(R.id.in_test);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                addItem();
                break;
        }
    }

    private void addItem() {
        list.add((list.size() + 1) + "");
        adapter.notifyDataSetChanged();
    }
}

class CusAdapter extends RecyclerView.Adapter<CusAdapter.CusViewHolder> {
    private List<String> list;

    public CusAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
        return new CusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CusViewHolder holder, int position) {
        String str = list.get(position);
        holder.setData(str);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CusViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public CusViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }

        public void setData(String str) {
            textView.setText(str);
        }
    }
}
