package com.academy.a06a_serrvices_createstartedservice

import android.os.*
import android.util.Log


class DownloadHandler(looper : Looper) : Handler(looper) {
    var mDownloadService: MyDownloadService?=null
    var mResultReceiver : ResultReceiver?=null

    override fun handleMessage(msg: Message) {
        downloadSong(msg.obj.toString())
//        mDownloadService?.stopSelf()
//        mDownloadService?.stopSelf(msg.arg1)  //with StartId
        // Stop Service for most recent StartId (msg.arg1)
        val stopSelfResult = mDownloadService?.stopSelfResult(msg.arg1)
        Log.d("DownloadHandler", "handleMessage, stopSelfResult = $stopSelfResult")

        val bundle = Bundle()
        bundle.putString(MainActivity.MESSAGE_KEY,msg.obj.toString())
        // Trigger callback to onReceiveResult in MyDownloadResultReceiver
        mResultReceiver?.send(MainActivity.RESULT_OK,bundle)
        super.handleMessage(msg)
    }

    private fun downloadSong( song : String){
        Log.d("DownloadHandler", "Starting Download - $song")
        Thread.sleep(4000)
        Log.d("DownloadHandler", "Download Complete - $song")
    }
}