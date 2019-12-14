package me.apqx.demo.widget.list;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.apqx.demo.R;

public class ListAdapter extends ArrayAdapter<Student> {

    private int resource;
    private List<Student> list;

    public ListAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        View view = null;
        if (convertView == null) {
            // 第一次加载View，并非是复用已有的View
            view = LayoutInflater.from(getContext()).inflate(resource, null, false);
            viewHolder = new ViewHolder(view);
            // 将ViewHolder保存在View里
            view.setTag(viewHolder);
        } else {
            // 使用已加载好的View
            view = convertView;
            // 取出里面保存的ViewHolder
            viewHolder = (ViewHolder) view.getTag();
        }
        // 给View设置数据
        if (position == getCount() - 1) {
            viewHolder.showFoot();

        } else {
            viewHolder.showNormal();
            Student student = list.get(position);
            viewHolder.setData(student);

        }
        // 返回要加载的View
        return view;
    }

    @Override
    public int getCount() {
        return list.size() + 1;
    }

    class ViewHolder {
        private ViewGroup vg_container;
        private TextView tvAge;
        private TextView tvName;

        public ViewHolder(View view) {
            vg_container = view.findViewById(R.id.cv_list_item);
            tvAge = view.findViewById(R.id.tv_list_age);
            tvName = view.findViewById(R.id.tv_list_name);
        }

        public void setData(Student student) {
            tvAge.setText(String.valueOf(student.getAge()));
            tvName.setText(student.getName());
        }

        public void showFoot() {
            vg_container.setBackgroundColor(Color.BLACK);
        }

        public void showNormal() {
            vg_container.setBackgroundColor(Color.WHITE);
        }
    }
}
