package com.academy.a05d_thread_create_workqueue
import android.os.Looper
import android.util.Log

class DownloadThread( activity: MainActivity) : Thread() {
    lateinit  var mHandler : DownloadHandler
    val mActivity = activity

    override fun run() {
        Looper.prepare()  // gives Looper for current Thread.
        mHandler = DownloadHandler(mActivity,Looper.myLooper()!!)
        Looper.loop()
    }
}