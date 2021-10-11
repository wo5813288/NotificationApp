package com.lollipop.notificationapp

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.lollipop.notificationapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val enabled = Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
        viewBinding.apply {
            switch1.isChecked = enabled.contains(NotificationCollectorService::class.java.getName())
            switch1.setOnCheckedChangeListener { _, b ->
                if (b){
                    if (!enabled.contains(NotificationCollectorService::class.java.getName())) {
                        startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
                    }
                }
            }
        }
    }
}

