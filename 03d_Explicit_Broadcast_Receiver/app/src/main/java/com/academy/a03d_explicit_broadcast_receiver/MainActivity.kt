package com.academy.a03d_explicit_broadcast_receiver

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1=findViewById<Button>(R.id.btn1)

        btn1.setOnClickListener{
            sendBroadcastMethod()
        }


    }
    private fun sendBroadcastMethod(){
//        val intent = Intent(this,ExampleBroadcastReceiver1::class.java)

//        intent.setClass(this,ExampleBroadcastReceiver1::class.java)
//        intent.component = ComponentName(this,ExampleBroadcastReceiver1::class.java)

        val intent= Intent();
        intent.component = ComponentName("com.academy.a03d_explicit_broadcast_receiver","com.academy.a03d_explicit_broadcast_receiver.ExampleBroadcastReceiver1")

        intent.putExtra("com.academy.a03d_explicit_broadcast_receiver.EXTRA_TEXT","Receiver(Self)")
        sendBroadcast(intent)
    }

}