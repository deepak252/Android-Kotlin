package com.academy.a03d_explicit_broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ExampleBroadcastReceiver1 : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        val text =  intent?.getStringExtra("com.academy.a03d_explicit_broadcast_receiver.EXTRA_TEXT");
        Toast.makeText(context,"EBR1 : $text",Toast.LENGTH_SHORT).show();
    }
}