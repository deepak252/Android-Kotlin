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
    private lateinit var mDownloadThread : DownloadThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        mDownloadThread = DownloadThread(this)
        mDownloadThread.name = "Download Thread"
        mDownloadThread.start()

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


//        val thread = Thread(object : Runnable{
//            override fun run() {
//                Log.d("","Download Started")
//                Thread.sleep(4000)
//                Log.d("","Download Complete")
//
//                runOnUiThread ( object : Runnable{
//                    override fun run() {
//                        showProgressIndicator(false)
//                    }
//                })
//            }
//        })
//        thread.start()

        for( song in Playlist.SONGS){
            val message = Message.obtain() // Avoid creating new Message instance
            message.obj = song
            mDownloadThread.mHandler.sendMessage(message)
        }

    }


    public fun logMessage(message:String){
        edtCode.append(message)
        scrollToEnd()
    }

    private fun scrollToEnd(){
        edtCode.scrollTo(0,edtCode.bottom)
    }

    private fun clearCode(){
        edtCode.setText("")
    }

    fun showProgressIndicator(loading:Boolean){
        progressIndicator.visibility= if(loading)  View.VISIBLE else View.INVISIBLE
    }
}