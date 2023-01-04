package com.academy.a06a_serrvices_createstartedservice

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log


class DownloadHandler(looper : Looper) : Handler(looper) {
    override fun handleMessage(msg: Message) {
        downloadSong(msg.obj.toString())
        super.handleMessage(msg)
    }

    private fun downloadSong( song : String){
        Log.d("DownloadHandler", "Starting Download - $song")
        Thread.sleep(4000)
        Log.d("DownloadHandler", "Download Complete - $song")
    }
}