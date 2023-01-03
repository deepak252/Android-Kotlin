package com.academy.a06a_serrvices_createstartedservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Message
import android.util.Log

class MyDownloadService : Service() {
    var mDownloadThread : DownloadThread?=null

    override fun onCreate() {
        super.onCreate()
        Log.d("MyDownloadService","onCreate Called")
        mDownloadThread = DownloadThread()
        mDownloadThread?.start()
        while(mDownloadThread!!.mHandler==null){

        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val song =intent?.getStringExtra(MainActivity.MESSAGE_KEY)
        Log.d("MyDownloadService","onStartCommand Called")
//        Log.d("Thread Name: ",Thread.currentThread().name)
        if(song!=null){
            val message = Message()
            message.obj=song
            mDownloadThread!!.mHandler?.sendMessage(message)
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

}