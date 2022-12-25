package com.academy.a05b_threads_backgroundthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {
    private lateinit var edtCode : EditText
    private lateinit var btnRun : Button
    private lateinit var btnClear : Button
    private lateinit var progressIndicator : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtCode = findViewById(R.id.edtCode)
        btnRun = findViewById(R.id.btnRun)
        btnClear = findViewById(R.id.btnClear)
        progressIndicator = findViewById(R.id.progressIndicator)
        showProgressIndicator(false)

        edtCode.setText(R.string.lorem_ipsum)

        btnClear.setOnClickListener{
            clearCode()
        }

        btnRun.setOnClickListener{
            runCode()
        }

    }

    private fun runCode(){
        logMessage("\nRunning Code")
        showProgressIndicator(true)
//        Thread.sleep(4000)  // Blocks UI thread (Main Thread)

//        val thread =object : Thread(){
//            override fun run() {
//                super.run()
//                Log.d("Background Thread", "Started");
//                sleep(4000)
//                Log.d("Background Thread", "Completed");
////                showProgressIndicator(false)  // UI thread not accessible
//            }
//        }
//        thread.start()

        val runnable =object : Runnable{
            override fun run() {
                Log.d("Background Thread", "Started")
                Thread.sleep(4000)
                Log.d("Background Thread", "Completed")
            }
        }
        val thread = Thread(runnable)
        thread.start()

    }


    private fun logMessage(message:String){
        edtCode.append(message)
        scrollToEnd()
    }

    private fun scrollToEnd(){
        edtCode.scrollTo(0,edtCode.bottom)
    }

    private fun clearCode(){
        edtCode.setText("")
    }

    private fun showProgressIndicator(loading:Boolean){
        progressIndicator.visibility= if(loading)  View.VISIBLE else View.INVISIBLE
    }
}