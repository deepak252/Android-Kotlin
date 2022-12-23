package com.academy.a03d_explicit_broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ExampleBroadcastReceiver2 : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"EBR2 Triggered", Toast.LENGTH_SHORT).show();
    }
}