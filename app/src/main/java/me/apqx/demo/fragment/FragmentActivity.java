package me.apqx.demo.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import me.apqx.demo.LogUtil;
import me.apqx.demo.R;

public class FragmentActivity extends androidx.fragment.app.FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.INSTANCE.d("activity onActivityResult " + requestCode + " : " + resultCode);
    }
}
