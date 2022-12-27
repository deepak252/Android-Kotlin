package com.academy.a05d_thread_create_workqueue
import android.os.Looper
import android.util.Log

class DownloadThread() : Thread() {
    lateinit  var mHandler : DownloadHandler

    override fun run() {
        Looper.prepare()  // gives Looper for current Thread.
        mHandler = DownloadHandler(Looper.myLooper()!!)
        Looper.loop()
    }

    private fun downloadSong( song : String){
        Log.d("DownloadThread", "Starting Download - $song")
        Thread.sleep(4000)
        Log.d("DownloadThread", "Download Complete - $song")
    }
}