package me.apqx.libwidget;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import me.apqx.libwidget.indicator.PagerIndicator;

public class MainActivityJ extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.vp_demo);
        PagerAdapter adapter = new DemoPagerAdapter();
        viewPager.setAdapter(adapter);
        PagerIndicator pagerIndicator = findViewById(R.id.pi_pager_indicator);
        pagerIndicator.bindViewPager(viewPager);
    }

    static class DemoPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 5;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = new View(container.getContext());
            view.setBackgroundColor(position % 2 == 0 ? Color.RED : Color.GREEN);
            container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return super.instantiateItem(container, position);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}
