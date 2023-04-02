package com.dcapp.a08a_coroutinesexample

import android.util.Log
import kotlinx.coroutines.*
// Jobs Hierarchy & Cancellation
object D {
    fun performTask(){
        CoroutineScope(Dispatchers.Main).launch {
            task2()
//            task1()
        }
    }
    // Cancel Job while something running
    suspend fun task2(){
        var job = CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..100000){
                if(isActive){
                    Log.d("MyTag", i.toString())
                }else{
                    break
                }
//                Log.d("MyTag", i.toString())
            }
        }
        delay(100)
        job.cancel()

    }
    // Jobs hierarchy & cancellation
    suspend fun task1(){
        var parentJob = CoroutineScope(Dispatchers.IO).launch {
            Log.d("MyTag", "Parent  ${Thread.currentThread().name}")
            var childJob1 = async {                 //Inherit parent context by default
                getFollowers("Child 1 ${Thread.currentThread().name}")
            }
//            childJob1.cancel()
            launch {
                getFollowers("Child 2 ${Thread.currentThread().name}")
            }

        }
        delay(1000)
        parentJob.cancel()


    }



    suspend fun getFollowers(label : String=""):Int{
        Log.d("MyTag", "$label getFollowers : started")
        delay(3000)
        Log.d("MyTag", "$label getFollowers : stopped")
        return 65
    }
}