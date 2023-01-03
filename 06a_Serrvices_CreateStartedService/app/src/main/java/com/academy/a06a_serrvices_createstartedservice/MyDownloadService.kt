package com.academy.a06a_serrvices_createstartedservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyDownloadService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val song =intent?.getStringExtra(MainActivity.MESSAGE_KEY)
        if(song!=null){
            downloadSong(song)
        }
//        return super.onStartCommand(intent, flags, startId)
        return Service.START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    private fun downloadSong( song : String){
        Log.d("MyDownloadService", "Starting Download - $song")
        Thread.sleep(4000)
        Log.d("MyDownloadService", "Download Complete - $song")
    }
}