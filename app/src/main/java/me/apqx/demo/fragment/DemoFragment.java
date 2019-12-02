package me.apqx.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import me.apqx.demo.LogUtil;
import me.apqx.demo.R;
import me.apqx.demo.widget.WidgetActivity;

public class DemoFragment extends Fragment {
    private static final int REQUEST_CODE = 1;
    private Button btn_start;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_demo, container, false);
        btn_start = view.findViewById(R.id.btn_start);
        btn_start.setOnClickListener((it) -> {
            startActivityForResult(new Intent(getActivity(), WidgetActivity.class), REQUEST_CODE);
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.INSTANCE.d("fragment onActivityResult " + requestCode + " : " + resultCode);
    }
}
