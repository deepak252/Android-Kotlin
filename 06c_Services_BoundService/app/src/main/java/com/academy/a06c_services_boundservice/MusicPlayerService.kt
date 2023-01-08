package com.academy.a06c_services_boundservice

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MusicPlayerService : Service() {
    val mBinder = MyServiceBinder()
    var mPlayer : MediaPlayer?=null
    companion object{
        val MUSIC_COMPLETE = "MusicComplete"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("MusicPlayerService","onCreate")
        // As onCreate will called for both Started Service and Bound Service
        mPlayer = MediaPlayer.create(this,R.raw.music1)
        //Trigger broadcast message - done, on music complete
        mPlayer!!.setOnCompletionListener {
            val intent = Intent(MUSIC_COMPLETE)
            intent.putExtra(MainActivity.MESSAGE_KEY, "done")
            LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
        }

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
        super.onDestroy()
        Log.d("MusicPlayerService","onDestroy")
        mPlayer?.release()
    }

    // PUBLIC CLIENT METHODS
    fun isPlaying(): Boolean{
        return mPlayer?.isPlaying?:false
    }
    fun play(){
        mPlayer?.start()
    }
    fun pause(){
        mPlayer?.pause()
    }

}