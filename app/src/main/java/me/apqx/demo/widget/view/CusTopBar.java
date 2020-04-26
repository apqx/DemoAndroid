package me.apqx.demo.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.apqx.demo.R;

public class CusTopBar extends FrameLayout {
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_subtitle)
    TextView tvSubtitle;

    public CusTopBar(Context context) {
        super(context);
        init(null);
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
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(view, layoutParams);
        ButterKnife.bind(this);
        if (attrs == null) return;

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CusTopBar);
        String title = typedArray.getString(R.styleable.CusTopBar_title);
        Drawable iconDrawable = typedArray.getDrawable(R.styleable.CusTopBar_iconRes);

        typedArray.recycle();
    }
}
