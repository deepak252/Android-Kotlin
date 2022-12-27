package com.academy.a05d_thread_create_workqueue

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

class DownloadHandler(looper: Looper) : Handler(looper) {
    override fun handleMessage(msg: Message) {
        downloadSong(msg.obj.toString())
    }

    private fun downloadSong( song : String){
        Log.d("DownloadThread", "Starting Download - $song")
        Thread.sleep(4000)
        Log.d("DownloadThread", "Download Complete - $song")
    }
}