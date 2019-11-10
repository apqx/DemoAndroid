package me.apqx.demo.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.apqx.demo.R;

public class CusTopBar extends FrameLayout {
    public CusTopBar(Context context) {
        super(context);
    }

    public CusTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CusTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_topbar, this, false);
        Button btn_back = view.findViewById(R.id.btn_back);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CusTopBar);
        String title = typedArray.getString(R.styleable.CusTopBar_title);
        String backText = typedArray.getString(R.styleable.CusTopBar_backText);
        tv_title.setText(title);
        btn_back.setText(backText);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(view, layoutParams);
    }
}
