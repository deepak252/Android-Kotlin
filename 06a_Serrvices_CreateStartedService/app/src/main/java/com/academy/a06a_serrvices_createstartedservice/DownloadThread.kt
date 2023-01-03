package com.academy.a06a_serrvices_createstartedservice

import android.os.Looper

class DownloadThread : Thread() {
    var mHandler : DownloadHandler?=null

    override fun run() {
        Looper.prepare()
        mHandler = DownloadHandler(Looper.myLooper()!!)
        Looper.loop()
    }
}