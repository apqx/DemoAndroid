package me.apqx.demo.widget.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.apqx.demo.LogUtil;

public class CusHorizontalAdapter extends HorizontalPager.Adapter {

    private Context context;

    public CusHorizontalAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public View getView(int position) {
        LogUtil.INSTANCE.d("getView " + position);
        TextView textView = new TextView(context);
        textView.setText(String.valueOf(position));
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
