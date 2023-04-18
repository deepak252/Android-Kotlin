package com.dcapp.a08a_kotlinflows

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

// Shared Flow (Hot Flow)
object G {
    fun start(){
        CoroutineScope(Dispatchers.Main).launch {
            producer().collect{
                Log.d("MyTag", "Collector - $it")
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            val result = producer()
            delay(5000)
            result
                .collect{
                Log.d("MyTag", "Collector2 - $it")
            }
        }
    }
    // This flow is an Hot Flow
    private fun producer() : Flow<Int>{
        val mutableSharedFlow = MutableSharedFlow<Int>()
        GlobalScope.launch {
            for(i in 1..10){
                delay(1000)
//                Log.d("MyTag", "Emitter - $i")
                mutableSharedFlow.emit(i)
            }
        }
        return mutableSharedFlow
    }

//    private suspend fun producer() : Flow<Int>{
//        var mutableSharedFlow = MutableSharedFlow<Int>()
//        for(i in 1..10){
//            delay(1000)
//            Log.d("MyTag", "Emitter - $i")
//            mutableSharedFlow.emit(i)
//        }
//        return mutableSharedFlow
//    }

//    private fun producer() = flow<Int> {
//        val mutableSharedFlow = MutableSharedFlow<Int>()
//        for(i in 1..10){
//            delay(1000)
//            Log.d("MyTag", "Emitter - $i")
//            mutableSharedFlow.emit(i)
//        }
//    }
}