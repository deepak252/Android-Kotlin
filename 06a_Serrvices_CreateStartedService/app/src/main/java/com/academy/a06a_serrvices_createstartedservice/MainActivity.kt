package com.academy.a06a_serrvices_createstartedservice


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.localbroadcastmanager.content.LocalBroadcastManager
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
    var mReceiver  = object:BroadcastReceiver(){
        override fun onReceive(ctx: Context?, intent: Intent?) {
            val song = intent?.getStringExtra(MESSAGE_KEY)
            logMessage("\n"+song.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    override fun onStart() {
        super.onStart()
        LocalBroadcastManager.getInstance(applicationContext).registerReceiver(mReceiver, IntentFilter(DownloadHandler.SERVICE_MESSAGE));
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(applicationContext).unregisterReceiver(mReceiver)
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

        //Send Intent to MyDownloadService
        for(song in Playlist.songs){
            val intent = Intent(this,MyDownloadService::class.java)
            intent.putExtra(MESSAGE_KEY, song)
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

}