package me.apqx.demo.notification;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import me.apqx.demo.tools.LogUtil;
import me.apqx.demo.R;

public class NotificationActivity extends Activity {


    private static final String CHANNEL_ID = "apqx";
    private static final int REQUEST_CODE = 0;
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

    }

    public void onClick(View view) {
        if (!NotificationUtils.isNotificationEnabled(this)) {
            NotificationUtils.goToSet(this);
            LogUtil.INSTANCE.e("没有通知权限");
            return;
        }
        try {
            switch (view.getId()) {
                case R.id.btn_show_notifi:
                    showNotification();
                    break;
                case R.id.btn_show_notifi2:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showNotification() {
        createNotificationChannel(this);

        // 设置点击行为
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("title")
                .setContentText("content")
                .setContentIntent(pendingIntent)
                // 用户点击后，自动移除通知
                .setAutoCancel(true)
                // 在Android 8.0 之前，设置通知优先级
                // 在Android 8.0 之后，通知优先级在Channel中设置
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android 8.0以上，需要创建通知Channel，必须在弹出通知之前创建，App生命周期中，创建一次即可
            // 可以重复执行，重复的执行并不会产生影响
            String channelName = context.getResources().getString(R.string.notification_channel);
            String channelDescription = context.getResources().getString(R.string.notification_channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(channelDescription);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
