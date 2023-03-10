package com.academy.a05c_thread_backgroundthread_class

import android.util.Log

class DownloadThread() : Thread() {

    override fun run() {
//        super.run()
        //Using Single Thread for multiple songs download
        for(song in Playlist().songs){
            downloadSong(song)
        }
    }

    private fun downloadSong( song : String){
        Log.d("DownloadThread", "Starting Download - $song")
        Thread.sleep(4000)
        Log.d("DownloadThread", "Download Complete - $song")
    }
}