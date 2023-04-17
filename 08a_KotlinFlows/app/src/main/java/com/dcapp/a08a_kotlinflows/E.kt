package com.dcapp.a08a_kotlinflows

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

// Context Preservation/ Switching, Thread
object E {
    fun start(){
        CoroutineScope(Dispatchers.Main).launch {
            consumer()
        }
    }
    suspend fun consumer(){
        producer()
            .map {
                Log.d("MyTag", "Collector Thread (mapping) - ${Thread.currentThread().name}")
                delay(1000)
                it*1
            }
                // Above code will execute on IO Thread
            .flowOn(Dispatchers.IO)
            .filter {
                Log.d("MyTag", "Collector Thread (filter) - ${Thread.currentThread().name}")
                delay(1000)
                it<8
            }
            .flowOn(Dispatchers.IO)
            .collect{
                Log.d("MyTag", "Collector Thread - ${Thread.currentThread().name}")
                delay(1000)
            }
    }

    suspend fun producer() = flow<Int> {
        Log.d("MyTag", "Emitter Thread - ${Thread.currentThread().name}")
        delay(1000)
        emit(5)

    }
}