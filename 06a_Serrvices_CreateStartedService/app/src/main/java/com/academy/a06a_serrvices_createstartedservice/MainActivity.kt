package com.academy.a06a_serrvices_createstartedservice


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.academy.a05c_thread_backgroundthread_class.Playlist

class MainActivity : AppCompatActivity() {
    private lateinit var edtCode : EditText
    private lateinit var btnRun : Button
    private lateinit var btnClear : Button
    private lateinit var progressIndicator : ProgressBar
    companion object {
        const val MESSAGE_KEY = "message_key"
        const val RESULT_OK = 200
    }
    private var mHandler : Handler?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        mHandler= Handler(mainLooper)

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

        val mDownloadResultReceiver = MyDownloadResultReceiver(null)
        //Send Intent to MyDownloadService
        for(song in Playlist.songs){
            val intent = Intent(this,MyDownloadService::class.java)
            intent.putExtra(MESSAGE_KEY, song)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            intent.putExtra(Intent.EXTRA_RESULT_RECEIVER, mDownloadResultReceiver)
            startService(intent)
        }
    }


    private fun logMessage(message:String){
        edtCode.append(message)
        scrollToEnd()
    }

    private fun scrollToEnd(){
        edtCode.scrollTo(0,edtCode.bottom)
    }

    private fun clearCode(){
        showProgressIndicator(false)
        edtCode.setText("")
        val intent = Intent(this,MyDownloadService::class.java)
        //Stops service completely, onDestroy()
        stopService(intent)
    }

    private fun showProgressIndicator(loading:Boolean){
        progressIndicator.visibility= if(loading)  View.VISIBLE else View.INVISIBLE
    }

    inner class MyDownloadResultReceiver(val handler : Handler?) : ResultReceiver(handler){
        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            super.onReceiveResult(resultCode, resultData)
            Log.d("MDownloadResultReceiver","onReceiveResult, ResultCode = $resultCode, resultData = $resultData, thread : ${Thread.currentThread().name}")
            if(resultCode ==  RESULT_OK && resultData!=null){
                val songName = resultData.getString(MESSAGE_KEY).toString()
//                runOnUiThread(object : Runnable{
//                    override fun run() {
//                        logMessage("\n"+songName)
//                    }
//                })
                mHandler?.post(object : Runnable{
                    override fun run() {
                        logMessage("\n"+songName)
                    }
                })
            }
        }
    }
}