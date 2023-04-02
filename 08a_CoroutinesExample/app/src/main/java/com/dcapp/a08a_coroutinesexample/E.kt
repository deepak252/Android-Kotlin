package com.dcapp.a08a_coroutinesexample

import android.util.Log
import kotlinx.coroutines.*

//withContext & runBlocking
object E {
    fun performTask(){
//        CoroutineScope(Dispatchers.Main).launch {
//            var b = task2()
//            Log.d("MyTag", "b = $b")
//        }
        task2()
    }

    suspend fun task1() : Int{
        var a = 0
        withContext(Dispatchers.IO){
            delay(2000)
            a = 20
        }
        return a
    }
    fun task2() : Int{
        var a = 0
        Log.d("MyTag", "1 : ${Thread.currentThread().name}")
        runBlocking {
            Log.d("MyTag", "2 : ${Thread.currentThread().name}")
//            delay(2000)
//            a=20
            launch {
                Log.d("MyTag", "3 : ${Thread.currentThread().name}")
                delay(2000)
                a=20
            }
        }
        Log.d("MyTag", "4 : ${Thread.currentThread().name}")
        return a
    }
}