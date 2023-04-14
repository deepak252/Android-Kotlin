package com.dcapp.a08a_kotlinflows

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow

// Cancel a Stream (Job)
object C {
    fun start(){
        consumer()
    }
    fun consumer(){
        val job = CoroutineScope(Dispatchers.IO).launch {
            var data = producer()
            data.collect{
                Log.d("MyTag", "Consumer $it")
            }
        }
        // Cancel job after 3500 ms
        GlobalScope.launch {
            delay(3500)
            job.cancel()
        }
    }

    fun producer() = flow<Int>{
        for(i in 1..7){
            delay(1000)
            emit(i)
        }
    }


}