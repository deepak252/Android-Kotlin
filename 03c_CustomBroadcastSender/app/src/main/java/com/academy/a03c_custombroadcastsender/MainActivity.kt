package com.academy.a03c_custombroadcastsender

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnSend).setOnClickListener{
            sendBroadcastMethod();
        }
    }

    private fun sendBroadcastMethod(){
        val intent = Intent("com.academy.a03c_custom_broadcast.EXAMPLE_ACTION");
        intent.putExtra("com.academy.a03c_custom_broadcast.EXTRA_TEXT","Broadcast Received from Sender App");
        sendBroadcast(intent);
    }


}