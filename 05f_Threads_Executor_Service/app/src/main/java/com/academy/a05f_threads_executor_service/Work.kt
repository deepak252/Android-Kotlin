package com.academy.a05f_threads_executor_service

import android.util.Log

class Work(val mThreadNo : Int) : Runnable {

    override fun run() {
        Log.d("Work", "${Thread.currentThread().name} Start, thread no : $mThreadNo")
        Thread.sleep(3000)
        Log.d("Work", "${Thread.currentThread().name} End, thread no : $mThreadNo")
    }
}