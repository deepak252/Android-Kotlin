package com.dcapp.a08a_kotlinflows

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// State Flow - maintains the last value of flow (state)
//  - Work on multiple threads, have more operators, lifecycle independent OTHER HAND Livedata has not these features.
object H{
    fun start(){
//        CoroutineScope(Dispatchers.IO).launch {
////            Log.d("MyTag", "CollectorI : ${producer().value}")
//            producer().collect{
//                Log.d("MyTag", "CollectorI : $it")
//            }
//        }
        CoroutineScope(Dispatchers.IO).launch {
            var data  = producer()
            delay(6000)
            data.collect{
                Log.d("MyTag", "CollectorII : $it")
            }
            Log.d("MyTag", "CollectorII value: ${data.value}")
        }
    }

    private suspend fun producer() : StateFlow<Int>{
        val mutableStateFlow = MutableStateFlow<Int>(0)
//        for(i in 1..10){
//            delay(1000)
//            Log.d("MyTag", "Emitter : $i")
//            mutableStateFlow.emit(i)
//        }
        GlobalScope.launch {
            for(i in 1..5){
                delay(1000)
                Log.d("MyTag", "Emitter : $i")
                mutableStateFlow.emit(i)
            }
        }

        return mutableStateFlow
    }
}