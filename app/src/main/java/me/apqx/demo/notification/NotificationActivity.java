package me.apqx.demo.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import java.util.Random;

import me.apqx.demo.LogUtil;
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
                    showNotificationJtw();
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
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

    private void showNotificationJtw() {
        Intent intent = new Intent(this, NotificationActivity.class);
        int keyCount = Math.abs(new Random().nextInt());
        PendingIntent pendingIntent = PendingIntent.getActivity(this, keyCount, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground);
        mBuilder.setAutoCancel(true);//点击后让通知将消失
        mBuilder.setContentTitle("Title");
        mBuilder.setContentText("Text");
        mBuilder.setTicker("Ticker");//设置显示的简略信息
        mBuilder.setOngoing(false);
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);

        //大文本
        mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText("BigText").setBigContentTitle("BigContentTitle"));

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(new Random().nextInt(), mBuilder.build());
    }

    private int id = 1;
    public void notification(View view) {
        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.edit);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        //设置小图标
        mBuilder.setSmallIcon(R.mipmap.edit);
        //设置大图标
        mBuilder.setLargeIcon(bitmap);
        //设置标题
        mBuilder.setContentTitle("这是标题");
        //设置通知正文
        mBuilder.setContentText("这是正文，当前ID是：" + id);
        //设置摘要
        mBuilder.setSubText("这是摘要");
        //设置是否点击消息后自动clean
        mBuilder.setAutoCancel(true);
        //显示指定文本
        mBuilder.setContentInfo("Info");
        //与setContentInfo类似，但如果设置了setContentInfo则无效果
        //用于当显示了多个相同ID的Notification时，显示消息总数
        mBuilder.setNumber(2);
        //通知在状态栏显示时的文本
        mBuilder.setTicker("在状态栏上显示的文本");
        //设置优先级
        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        //自定义消息时间，以毫秒为单位，当前设置为比系统时间少一小时
        mBuilder.setWhen(System.currentTimeMillis() - 3600000);
        //设置为一个正在进行的通知，此时用户无法清除通知
        mBuilder.setOngoing(true);
        //设置消息的提醒方式，震动提醒：DEFAULT_VIBRATE     声音提醒：NotificationCompat.DEFAULT_SOUND
        //三色灯提醒NotificationCompat.DEFAULT_LIGHTS     以上三种方式一起：DEFAULT_ALL
        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        //设置震动方式，延迟零秒，震动一秒，延迟一秒、震动一秒
        mBuilder.setVibrate(new long[]{0, 1000, 1000, 1000});

        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 0);
        mBuilder.setContentIntent(pIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id++, mBuilder.build());
    }
}
