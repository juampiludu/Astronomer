package com.example.astronomer

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class ReminderActivity : AppCompatActivity()  {

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

        val cal = Calendar.getInstance()
        val dateButton: Button = findViewById(R.id.dateButton)
        val dateView: TextView = findViewById(R.id.dateView)
        val timeButton: Button = findViewById(R.id.timeButton)
        val timeView: TextView = findViewById(R.id.timeView)

        timeButton.setOnClickListener {

            val timeSetListener = TimePickerDialog.OnTimeSetListener{timePicker, hour, minute ->

                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                timeView.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        dateButton.setOnClickListener {

            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val date =  DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay ->
                dateView.text = "$mDay/$mMonth/$mYear"
            }, year, month, day)
            date.show()
        }

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
