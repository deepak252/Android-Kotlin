package com.academy.a06b_services_intentservice

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class MyIntentService : IntentService("MyIntentService") {
    companion object{
        const val SERVICE_MESSAGE = "serviceMessage"
    }
    override fun onCreate() {
        super.onCreate()
        Log.d("onCreate","Thread Name = ${Thread.currentThread().name}")
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d("onHandleIntent","Thread Name = ${Thread.currentThread().name}")
        val song = intent?.getStringExtra(MainActivity.MESSAGE_KEY)
        if(song!=null){
            downloadSong(song)
            sendMessageToUI(song)
        }
    }

    private fun sendMessageToUI(msg: String){
        val intent = Intent(SERVICE_MESSAGE)
        intent.putExtra(MainActivity.MESSAGE_KEY,msg)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onCreate()
        Log.d("onDestroy","Thread Name = ${Thread.currentThread().name}")
    }

    private fun downloadSong( song : String){
        Log.d("downloadSong", "Starting Download - $song")
        Thread.sleep(4000)
        Log.d("downloadSong", "Download Complete - $song")
    }


}