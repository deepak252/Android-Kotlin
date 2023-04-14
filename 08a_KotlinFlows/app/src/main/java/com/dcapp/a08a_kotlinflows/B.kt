package com.dcapp.a08a_kotlinflows

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

// Kotlin Flows & Multiple consumers
object B {

    fun start(){
        CoroutineScope(Dispatchers.IO).launch {
            consumer()
//            consumer()
        }
        //Multiple Consumer
//        CoroutineScope(Dispatchers.IO).launch {
//            consumer()
//        }
    }

    private suspend fun consumer(){
        val data : Flow<Int> = producer()
        data.collect{
            Log.d("MyTag", "Consumer : $it")
        }
        Log.d("MyTag", "All Data Collected")
    }
    // Creating a Stream of Integers
    private fun producer() : Flow<Int> = flow<Int> {
        // By Default Flows create a Coroutine Scope
        for(i in 1..10){
            delay(1000)
            emit(i)
        }
    }
//    private fun producer() : Flow<Int> {
//        return flow<Int> {
//            val list = listOf<Int>(1,2,3,4,5,6,7,8)
//            for(i in 1..10){
//                delay(1000)
//                emit(i)
//            }
//        }
//    }


}