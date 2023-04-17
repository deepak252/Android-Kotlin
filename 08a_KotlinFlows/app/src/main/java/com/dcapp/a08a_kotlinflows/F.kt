package com.dcapp.a08a_kotlinflows

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

// Exception Handling
object F {
    fun start(){
        CoroutineScope(Dispatchers.Main).launch {
            consumer()
        }
    }
    suspend fun consumer(){
        try{
            producer()
                .map {
                    delay(1000)
                    it*2
                }
                .collect{
                    Log.d("MyTag", "Collector: $it")
                    delay(1000)
//                    throw Exception("Error In Collector")
                }
        }catch (e : Exception){
            Log.e("MyTag", "$e")
        }
    }

    suspend fun producer() = flow<Int> {
        delay(1000)
        emit(5)
        throw Exception("Error In Emitter")
    }.catch {
        Log.e("MyTag", "Emitter Catch - $it")
        emit(-1)
    }
}