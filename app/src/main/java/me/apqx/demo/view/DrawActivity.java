package me.apqx.demo.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import me.apqx.demo.R;
import me.apqx.libbase.util.ToastUtil;

public class DrawActivity extends Activity {
    private ImageView iv_canvas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        Switch mSwitch = findViewById(R.id.sw_switch);
        mSwitch.setOnCheckedChangeListener((buttonView, isChecked)
                -> ToastUtil.INSTANCE.showToast("Check变化 " + isChecked));
        findViewById(R.id.cusView).setOnClickListener(v -> {
            ToastUtil.INSTANCE.showToast("点击");
            mSwitch.setChecked(!mSwitch.isChecked());
        });
        mSwitch.setOnClickListener(view -> {});

    }

    /**
     * 保存文件到指定路径,并返回路径
     *
     * @param context
     * @param bitmap
     */
    public String saveMyBitmapForPath(Context context, Bitmap bitmap) {
        String sdCardDir = Environment.getExternalStorageDirectory() + "/DCIM/";
        File appDir = new File(sdCardDir, "pictures");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "picture" + System.currentTimeMillis() + ".jpg";
        File f = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return f.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            // 通知图库更新
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(f);
            intent.setData(uri);
            context.sendBroadcast(intent);

        }

    }
}
