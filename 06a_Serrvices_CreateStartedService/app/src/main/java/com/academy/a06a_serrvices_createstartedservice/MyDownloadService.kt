package com.academy.a06a_serrvices_createstartedservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyDownloadService : Service() {


    override fun onCreate() {
        super.onCreate()
        Log.d("MyDownloadService","onCreate Called")
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val song =intent?.getStringExtra(MainActivity.MESSAGE_KEY)
        Log.d("MyDownloadService","onStartCommand Called")
//        Log.d("Thread Name: ",Thread.currentThread().name)
        if(song!=null){
            val runnable = object : Runnable{
                override fun run() {
                    downloadSong(song)
                }
            }
            val thread = Thread(runnable)
            thread.start()
        }
//        return super.onStartCommand(intent, flags, startId)
        return Service.START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d("MyDownloadService","onBind Called")
        TODO("Return the communication channel to the service.")
    }

    override fun onDestroy() {
        Log.d("MyDownloadService","onDestroy Called")
        super.onDestroy()
    }

    private fun downloadSong( song : String){
        Log.d("MyDownloadService", "Starting Download - $song")
        Thread.sleep(4000)
        Log.d("MyDownloadService", "Download Complete - $song")
    }
}