package com.dcapp.a08a_coroutinesexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var btn1 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.btn1)

        btn1.setOnClickListener{
//            A.performTask()
            B.performTask()
//            D.performTask()
//            E.performTask()

        }


    }



//    fun fetch(){
////        Thread{
////            sleep()
////        }.start()
//
////        Thread(object : Runnable{
////            override fun run() {
////                sleep()
////            }
////        }).start()
//
////        thread(start=true){
////            sleep()
////        }
////
////        Handler(Looper.getMainLooper()).postDelayed(object : Runnable{
////            override fun run() {
////                sleep()
////            }
////        },2000)
//    }
}

fun sleep(tag : String="SLEEP", time : Long=2000){
    Log.d(tag, "${Thread.currentThread().name} Start")
    Thread.sleep(time)
    Log.d(tag, "${Thread.currentThread().name} Stop")
}