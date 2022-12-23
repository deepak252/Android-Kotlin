package com.academy.a03d_explicit_broadcast

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener{
            sendBroadcast();
        }
    }

    private fun sendBroadcast(){
//        val intent = Intent(this,ExampleBroadcastReceiver2::class.java)
        val intent = Intent()
//        intent.setClass(this,ExampleBroadcastReceiver2::class.java);

        val cn = ComponentName(this,ExampleBroadcastReceiver2::class.java)
        intent.component=cn
        sendBroadcast(intent);
    }
}