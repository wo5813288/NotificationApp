package com.lollipop.notificationapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.PowerManager
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log


/**
 * created by Lollipop
 * on 2021/10/11
 */
class NotificationCollectorService: NotificationListenerService() {
    companion object{
        const val UI_NOTIFICATION = "com.android.systemui"
    }
    private val mWakelock:PowerManager.WakeLock by lazy {
        val pm = getSystemService(Context.POWER_SERVICE) as PowerManager
        pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "target:notification")
    }
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        //监听到消息以后，通知亮屏
        //如果是系统通知，则不亮屏
        sbn?.run {
            if (sbn.packageName.equals(UI_NOTIFICATION)) {
                return
            }
            mWakelock.acquire(1000)
            mWakelock.release()
        }
    }
}