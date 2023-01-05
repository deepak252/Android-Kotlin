package com.academy.a06a_serrvices_createstartedservice

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import com.academy.a05c_thread_backgroundthread_class.Playlist

class MyDownloadService : Service() {
    var mDownloadThread : DownloadThread?=null

    override fun onCreate() {
        super.onCreate()
        Log.d("MyDownloadService","onCreate Called")
        mDownloadThread = DownloadThread()
        mDownloadThread?.start()
        while(mDownloadThread?.mHandler==null){
            Log.d("MyDownloadService","onCreate : mHandler is null")
        }
        mDownloadThread!!.mHandler!!.mDownloadService = this
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val song =intent?.getStringExtra(MainActivity.MESSAGE_KEY)
        Log.d("MyDownloadService","onStartCommand Called, startId = $startId")

        if(song!=null) {
            if(mDownloadThread?.mHandler!=null){
                val message = Message()
                message.obj = song
                message.arg1=startId
                mDownloadThread!!.mHandler!!.sendMessage(message)
            }else{
                Log.d("onStartCommand","mHandler is NULL")
            }
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