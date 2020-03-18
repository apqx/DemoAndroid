package me.apqx.demo.widget.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.apqx.libbase.util.LogUtil;

public class CusHorizontalAdapter extends HorizontalPager.Adapter {

    private List<String> list;
    private Context context;

    public CusHorizontalAdapter(Context context, List<String> strList) {
        this.context = context;
        this.list = strList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position) {
        LogUtil.INSTANCE.d("getView " + position + " : " + list.get(position));
        TextView textView = new TextView(context);
        textView.setText(list.get(position));
        textView.setBackgroundColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return textView;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
}
