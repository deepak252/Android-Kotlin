package com.dcapp.a08a_coroutinesexample

import android.util.Log
import kotlinx.coroutines.*
//Suspending Functions
object B {
    fun performTask(){
        CoroutineScope(Dispatchers.Main).launch {
            task1()
//            task2()
//            Log.d("MyTag", "Completed")
        }
        CoroutineScope(Dispatchers.Main).launch {
//            task1()
            task2()
        }
    }

    suspend fun task1(){
        Log.d("MyTag","Starting Task1")
        delay(1000)
//        yield()
        Log.d("MyTag","Stop Task1")
    }
    suspend fun task2(){
        Log.d("MyTag","Starting Task2")
        delay(2000)
//        yield()
        Log.d("MyTag","Stop Task2")
    }
}