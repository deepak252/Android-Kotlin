package com.academy.a03c_custom_broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

open class ExampleBroadcastReceiver : BroadcastReceiver() {
    val EXAMPLE_ACTION = "com.academy.a03c_custom_broadcast.EXAMPLE_ACTION";
    override fun onReceive(context: Context?, intent: Intent?) {
        if(EXAMPLE_ACTION==intent?.action){
            val receivedText= intent.getStringExtra("com.academy.a03c_custom_broadcast.EXTRA_TEXT");
            Toast.makeText(context,receivedText,Toast.LENGTH_SHORT).show();
        }
    }
}