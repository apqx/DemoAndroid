package me.apqx.demo.widget.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.apqx.demo.tools.LogUtil;
import me.apqx.demo.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CusViewHolder> {
    private int resource;
    private List<Student> list = new ArrayList<>();
    private OnItemClickListener listener;

    public RecyclerAdapter(int resource) {
        this.resource = resource;
    }

    public void setData(List<Student> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    @Override
    public CusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtil.INSTANCE.d("onCreateViewHolder");
        // 根据viewType加载不同类型的ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new CusViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull CusViewHolder holder, int position) {
        LogUtil.INSTANCE.d("onBindViewHolder");
        // 根据viewType绑定数据
        Student student = list.get(position);
        holder.setData(student);
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        // 获取item的类型，RecyclerView在加载每一个item的时候都会判断此item类型的ViewHolder是否已经创建过，是否充足，
        // 如果未创建过或数量不足，则创建新的ViewHolder，RecyclerView会自动根据item的类型加载对应的ViewHolder视图
        return 0;
    }

    class CusViewHolder extends RecyclerView.ViewHolder{

        private TextView tvAge;
        private TextView tvName;
        private View itemView;

        public CusViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            this.itemView = itemView;
            tvAge = itemView.findViewById(R.id.tv_list_age);
            tvName = itemView.findViewById(R.id.tv_list_name);
        }

        public void setData(Student student) {
            tvAge.setText(String.valueOf(student.getAge()));
            tvName.setText(student.getName());
            // RecycelrView本身不提供点击监听器，需要手动实现
            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    // 把具体点击的数据发送到外部，方便修改数据
                    listener.onClick(student);
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // item被点击时的监听器
    public interface OnItemClickListener {
        // 把点击的item数据传入
        void onClick(Student student);
    }
}
