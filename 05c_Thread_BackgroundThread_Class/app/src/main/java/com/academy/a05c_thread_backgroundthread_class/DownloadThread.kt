package com.academy.a05c_thread_backgroundthread_class

import android.util.Log

class DownloadThread(private val song : String) : Thread() {

    override fun run() {
//        super.run()
        downloadSong()
    }

    private fun downloadSong(){
        Log.d("DownloadThread", "Starting Download - $song")
        Thread.sleep(4000)
        Log.d("DownloadThread", "Download Complete - $song")
    }
}