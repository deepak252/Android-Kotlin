package com.dcapp.a08a_kotlinflows

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

// Flow Operators -Events, Map, Filter, Buffering
object D {
    fun start(){
        CoroutineScope(Dispatchers.IO).launch {
            consumer()
        }
    }

    suspend fun consumer(){
//        var first = producer().first()
//        Log.d("MyTag", "consumer : First - $first")
//        var list = producer().toList()
//        Log.d("MyTag", "consumer : List - $list")

//        producer()
//                //Events - onStart, onCompletion, onEach
//            .onStart {
//                Log.d("MyTag", "consumer : Started")
//            }
//            .onCompletion {
//                Log.d("MyTag", "consumer : Completed")
//            }
//            .onEach {
//                Log.d("MyTag", "consumer : about to emit- $it")
//            }
//            .map {
//                it*2
//            }   // 2,4,6,8,....
//            .filter {
//                it<8
//            }  // 2,4,6
//            .collect{
//                Log.d("MyTag", "consumer : collect- $it")
//            }

        var time = measureTimeMillis {
            // here Producer is FAST but Consumer is SLOW
            producer()
                .buffer(5) // 5 items to buffer
                .collect{
                    delay(2000)
                    Log.d("MyTag", "consumer : collect- $it")
                }
        }
        Log.d("MyTag", "Time Taken : $time")
    }

    suspend fun producer() = flow<Int> {
        for(i in 1..10){
            delay(1000)
            Log.d("MyTag", "Producer : emit - $i")
            emit(i)
        }
    }
}