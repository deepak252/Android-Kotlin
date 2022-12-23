package com.academy.a03b_dynamic_broadcast_receiver

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var exampleBroadcastReceiver  = ExampleBroadcastReceiver();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // When app goes back to foreground
    override fun onStart() {
        super.onStart()
//        Toast.makeText(this,"onStart", Toast.LENGTH_SHORT).show();
        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        filter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(exampleBroadcastReceiver,filter);
    }


    // When app goes go background
    override fun onStop() {
        super.onStop()
//        Toast.makeText(this,"onStop", Toast.LENGTH_SHORT).show();
        unregisterReceiver(exampleBroadcastReceiver);
    }
}