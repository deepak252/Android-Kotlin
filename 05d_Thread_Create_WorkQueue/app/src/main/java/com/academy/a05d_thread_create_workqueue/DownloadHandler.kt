package com.academy.a05d_thread_create_workqueue

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

class DownloadHandler(activity: MainActivity,looper: Looper) : Handler(looper) {
    val mActivity = activity

    override fun handleMessage(msg: Message) {
        downloadSong(msg.obj.toString())
    }

    private fun downloadSong( song : String){
        Log.d("DownloadThread", "Starting Download - $song")
        Thread.sleep(4000)
        Log.d("DownloadThread", "Download Complete - $song")

//        val handler = Handler(Looper.getMainLooper())
//        handler.post(object : Runnable{
//            override fun run() {
//        mActivity.logMessage("Download Complete")
//                mActivity.showProgressIndicator(false)
//            }
//        })

        mActivity.runOnUiThread(object : Runnable{
            override fun run() {
                mActivity.logMessage("\nDownload Complete $song")
                mActivity.showProgressIndicator(false)
            }
        })
    }
}