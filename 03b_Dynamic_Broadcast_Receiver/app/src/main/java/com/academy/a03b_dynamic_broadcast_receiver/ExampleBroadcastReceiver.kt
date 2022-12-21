package com.academy.a03b_dynamic_broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ExampleBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if(Intent.ACTION_AIRPLANE_MODE_CHANGED ==intent?.action){
            val isAirplaneMode = intent.getBooleanExtra("state",false);
            Toast.makeText(context,"Airplane mode ${if(isAirplaneMode) "enabled" else "disabled"}", Toast.LENGTH_SHORT).show();
        }
    }
}