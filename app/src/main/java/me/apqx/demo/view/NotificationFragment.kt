package me.apqx.demo.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.frag_notification.*
import me.apqx.demo.MainActivity
import me.apqx.demo.R
import me.apqx.demo.mvp.BaseFragment
import me.apqx.demo.mvp.BasePresenter
import me.apqx.demo.mvp.IBaseView
import me.apqx.demo.old.tools.NotificationUtils
import me.apqx.demo.old.tools.LogUtil

class NotificationFragment : BaseFragment<BasePresenter<IBaseView>>() {
    private val channelId = "apqx"
    private val requestCode = 0
    private val notificationId = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_notification, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_show_notify.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_show_notify -> {
                if (!NotificationUtils.isNotificationEnabled(context)) {
                    NotificationUtils.goToSet(context)
                    LogUtil.e("没有通知权限")
                    return
                }
                showSimpleNotification(context!!)
            }
        }
    }

    private fun showSimpleNotification(context: Context) {
        createNotificationChannel(context)

        // 设置点击行为 TODO: Deep Link
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.baseline_expand_more_black_24)
//                .setLargeIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("title")
                .setContentText("content")
                .setContentIntent(pendingIntent)
                // 用户点击后，自动移除通知
                .setAutoCancel(true)
                // 在Android 8.0 之前，设置通知优先级
                // 在Android 8.0 之后，通知优先级在Channel中设置
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        val notificationManager = NotificationManagerCompat.from(context)

        notificationManager.notify(notificationId, builder.build())
    }

    private fun showHandsUpNotification(context: Context) {

    }

    /**
     * 在Android 8.0以上，必须创建通知Channel，要弹出的通知，必须指定已经注册的通知Channel
     */
    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android 8.0以上，需要创建通知Channel，必须在弹出通知之前创建，App生命周期中，创建一次即可
            // 可以重复执行，重复的执行并不会产生影响
            val channelName = context.resources.getString(R.string.notification_channel)
            val channelDescription = context.resources.getString(R.string.notification_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance)
            channel.description = channelDescription
            val notificationManager = context.getSystemService<NotificationManager>(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}