package com.example.astronomer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ReminderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var notificationManager: NotificationManager
        lateinit var notificationChannel: NotificationChannel
        lateinit var builder: Notification.Builder
        val channelId = "com.example.astronomer"
        val description= "Notification"

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val actionBar = supportActionBar
        actionBar!!.title = "Reminder"

        actionBar.setDisplayHomeAsUpEnabled(true)

        val notifButton: Button = findViewById(R.id.notifButton)
        notifButton.setOnClickListener {

            val intent = Intent(applicationContext, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.BLUE
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this, channelId)
                    .setContentTitle("New Notification")
                    .setContentText("This is a notification of Astronomer app")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)

            } else {
                builder = Notification.Builder(this)
                    .setContentTitle("New Notification")
                    .setContentText("This is a notification of Astronomer app")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)
            }
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                notificationManager.notify(0, builder.build())
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
