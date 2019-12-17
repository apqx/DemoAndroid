package me.apqx.demo.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.apqx.demo.R;

public class StagAdapter extends RecyclerView.Adapter<StagAdapter.CusViewHolder> {

    private List<String> list;

    public StagAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_stag, parent, false);
        return new CusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CusViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CusViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public CusViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_stag);
        }

        public void setData(String str) {
            tv.setText(str);
        }
    }
}
