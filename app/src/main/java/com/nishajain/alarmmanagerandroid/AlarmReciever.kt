package com.nishajain.alarmmanagerandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReciever : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        //you can check the log that it is fired
        //Here we are actually not doing anything
        //but you can do any task here that you want to be done at a specific time everyday
        Log.d("MyAlarm", "Alarm is just fired");
    }

}