package com.academy.a06c_services_boundservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MusicPlayerService : Service() {
    val mBinder = MyServiceBinder()

    override fun onCreate() {
        Log.d("MusicPlayerService","onCreate")
        super.onCreate()
    }
    // Binder class implements IBinder Interface
    inner class MyServiceBinder : Binder(){
        fun getService() : MusicPlayerService{
            return this@MusicPlayerService
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MusicPlayerService","onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d("MusicPlayerService","onBind")
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("MusicPlayerService","onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.d("MusicPlayerService","onDestroy")
        super.onDestroy()
    }
}