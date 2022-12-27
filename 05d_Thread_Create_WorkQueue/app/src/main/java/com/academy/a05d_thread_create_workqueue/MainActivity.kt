package com.academy.a05d_thread_create_workqueue


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
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
//    private lateinit var handler: Handler
//    private var MESSAGE_KEY = "message_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
//        // Looper loops on Work Queue
//        // Handler manages the Looper
//        //** This "handler" is attached on Main Thread, so passing Main Thread Looper (getMainLooper)
//        handler =object : Handler(Looper.getMainLooper()){
//            override fun handleMessage(msg: Message) {
////                super.handleMessage(msg)
//                Log.d("handleMessage", "${msg.data.getString(MESSAGE_KEY)}")
//                // Message Received from Background Thread -> Update UI
//                logMessage("\n${msg.data.getString(MESSAGE_KEY)}")
//                showProgressIndicator(false)
//            }
//        }

    }

    private fun initViews(){
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

        val downloadThread = DownloadThread()
        downloadThread.name = "Download Thread"
        downloadThread.start()

        showProgressIndicator(false)

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