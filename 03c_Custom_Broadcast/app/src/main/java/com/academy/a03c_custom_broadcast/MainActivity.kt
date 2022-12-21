package com.academy.a03c_custom_broadcast

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var tvMessageReceived : TextView?=null;
    // Anonymous Inner Class
    var exampleBroadcastReceiver = object : ExampleBroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            super.onReceive(context, intent)

            tvMessageReceived?.text = intent?.getStringExtra("com.academy.EXTRA_TEXT")
        }
    };
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMessageReceived = findViewById(R.id.tvMessageReceived);

        val filter = IntentFilter(exampleBroadcastReceiver.EXAMPLE_ACTION);
        registerReceiver(exampleBroadcastReceiver,filter);

        val btnSend = findViewById<Button>(R.id.btnSend);
        btnSend.setOnClickListener{
            sendBroadcastToSelf()
        }

    }

    private  fun sendBroadcastToSelf(){
        val intent = Intent(exampleBroadcastReceiver.EXAMPLE_ACTION);
        intent.putExtra("com.academy.EXTRA_TEXT","Broadcast Received");
        sendBroadcast(intent);
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(exampleBroadcastReceiver);
    }
}