package com.academy.a06a_serrvices_createstartedservice

import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class DownloadHandler(looper : Looper) : Handler(looper) {
    var mDownloadService: MyDownloadService?=null
    companion object{
        val SERVICE_MESSAGE = "serviceMessage"
    }
    var mContext : Context?=null

    override fun handleMessage(msg: Message) {
        downloadSong(msg.obj.toString())
//        mDownloadService?.stopSelf()
//        mDownloadService?.stopSelf(msg.arg1)  //with StartId
        // Stop Service for most recent StartId (msg.arg1)
        val stopSelfResult = mDownloadService?.stopSelfResult(msg.arg1)
//        Log.d("DownloadHandler", "handleMessage, stopSelfResult = $stopSelfResult")
        sendBroadcastMessage(msg.obj.toString())
        super.handleMessage(msg)
    }

    private fun sendBroadcastMessage(msg : String){
        if(mContext!=null){
            val intent = Intent(SERVICE_MESSAGE)
            intent.putExtra(MainActivity.MESSAGE_KEY, msg)
            //Local Broadcast Receiver - message to receivers listening to "serviceMessage" key
            // i.e. send message to MainActivity
            LocalBroadcastManager.getInstance(mContext!!).sendBroadcast(intent)
        }else{
            Log.d("DownloadHandler", "sendBroadcastMessage, context is null")
        }
    }

    private fun downloadSong( song : String){
        Log.d("DownloadHandler", "Starting Download - $song")
        Thread.sleep(4000)
        Log.d("DownloadHandler", "Download Complete - $song")
    }
}