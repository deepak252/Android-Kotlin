package com.dcapp.a08a_kotlinflows

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

// Channel example
object A {
    private var channel =  Channel<Int>()

    fun producer(){
        CoroutineScope(Dispatchers.Main).launch {
            channel.send(5)
            channel.send(10)
        }
    }

    fun consumer(){
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("MyTag","Received1 ${channel.receive().toString()}")
            Log.d("MyTag","Received2 ${channel.receive().toString()}")
        }
    }

//    fun runSuspendedTask(){
//        CoroutineScope(Dispatchers.IO).launch {
//            val users = getUsers()
//            Log.d("MyTag", users.toString())
//        }
//    }
//    private suspend fun getUsers() : List<String>{
//        var users = mutableListOf<String>();
//        users.add(getUser(1))
//        users.add(getUser(2))
//        users.add(getUser(3))
//        return users
//    }
//
//    private suspend fun getUser(userId : Int):String{
//        delay(2000)
//        return "User $userId"
//    }
}