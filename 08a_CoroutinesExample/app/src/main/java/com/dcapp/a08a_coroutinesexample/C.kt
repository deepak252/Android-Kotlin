package com.dcapp.a08a_coroutinesexample

import android.util.Log
import kotlinx.coroutines.*

// Coroutine Builders
object C {
    fun performTask(){
        CoroutineScope(Dispatchers.Main).launch {
            fetchDataAsync2()
//            fetchDataAsync1()
//            fetchData()
        }
    }
    suspend fun fetchDataAsync2(){
        Log.d("MyTag", "fetchDataAsync2 : started")
        CoroutineScope(Dispatchers.Main).launch {
            var a = async { getFollowers() }
            var b = async { getFollowers() }
//            var a = getFollowers()
//            var b = getFollowers()
//            Log.d("MyTag", "fetchDataAsync2, Followers : $a , $b")
            Log.d("MyTag", "fetchDataAsync2, Followers : ${a.await()} , ${b.await()}")

        }
//        delay(1000)
//        job.cancel()
        Log.d("MyTag", "fetchDataAsync2 : stopped")
    }


    suspend fun fetchDataAsync1(){
        var followers = 0
        Log.d("MyTag", "fetchDataAsync : started")
        var job = CoroutineScope(Dispatchers.Main).async {
            followers = getFollowers()
            followers
        }
//        delay(1000)
//        job.cancel()
        Log.d("MyTag", "fetchDataAsync : stopped")
        Log.d("MyTag", "fetchDataAsync, Followers : ${job.await()}")
    }

    suspend fun fetchData(){
        var followers = 0
        Log.d("MyTag", "fetchData : started")
        var job1 = CoroutineScope(Dispatchers.Main).launch {
            followers = getFollowers()
        }
//        delay(1000)
//        job1.cancel()
        job1.join()
        Log.d("MyTag", "fetchData : stopped")
        Log.d("MyTag", "fetchData, Followers : ${followers}")
    }

    suspend fun getFollowers():Int{
        Log.d("MyTag", "getFollowers : started")
        delay(2000)
        Log.d("MyTag", "getFollowers : stopped")

        return 65
    }
}
