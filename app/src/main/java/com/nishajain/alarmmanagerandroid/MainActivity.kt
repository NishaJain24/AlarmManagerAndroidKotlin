package com.nishajain.alarmmanagerandroid

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.os.Build
import android.view.View
import android.widget.Button

import android.widget.TimePicker
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Instantiate the views TimePicker and Button
        val timePicker = findViewById<TimePicker>(R.id.timePicker)
        val setAlarm = findViewById<Button>(R.id.buttonAlarm)

        //attaching clicklistener on setAlarm button
        setAlarm.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //We need a calendar object to get the specified time in millis
                //as the alarm manager method takes time in millis to setup the alarm
                val calendar: Calendar = Calendar.getInstance()
                if (Build.VERSION.SDK_INT >= 23) {
                    calendar.set(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        timePicker.hour,
                        timePicker.minute,
                        0
                    )
                } else {
                    calendar.set(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        timePicker.currentHour,
                        timePicker.currentMinute,
                        0
                    )
                }
                setAlarm(calendar.getTimeInMillis())
            }
        })
    }

    private fun setAlarm(time: Long) {
        //getting the alarm manager
        val am = getSystemService(ALARM_SERVICE) as AlarmManager

        //creating a new intent specifying the broadcast receiver
        val i = Intent(this, AlarmReciever::class.java)

        //creating a pending intent using the intent
        val pi = PendingIntent.getBroadcast(this, 0, i, 0)

        //setting the repeating alarm that will be fired every day
        am.setRepeating(AlarmManager.RTC, time, AlarmManager.INTERVAL_DAY, pi)
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show()
    }
}