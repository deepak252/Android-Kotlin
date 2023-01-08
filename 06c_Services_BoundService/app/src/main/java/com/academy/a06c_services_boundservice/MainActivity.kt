package com.academy.a06c_services_boundservice


import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {
    private lateinit var edtCode : EditText
    private lateinit var btnRun : Button
    private lateinit var btnClear : Button
    private lateinit var progressIndicator : ProgressBar
    companion object {
        const val MESSAGE_KEY = "message_key"
    }
    private var mMusicPlayerService : MusicPlayerService?=null
    private var mBound = false
    private val mServiceConnection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, iBinder: IBinder?) {
            val myServiceBinder : MusicPlayerService.MyServiceBinder = iBinder as MusicPlayerService.MyServiceBinder
            mMusicPlayerService = myServiceBinder.getService()
            mBound = true
            Log.d("ServiceConnection", "onServiceConnected : Connected")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            // Triggered on unexpected crash, not on unBind()
            Log.d("ServiceConnection", "onServiceConnected : Disconnected")
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MusicPlayerService::class.java)
        // Will call onBind() method from MusicPlayerService class
        bindService(intent,mServiceConnection,Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if(mBound){
            unbindService(mServiceConnection)
            mBound=false
        }
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
//        for(song in Playlist.songs){
//            val intent = Intent(this,MyIntentService ::class.java)
//            intent.putExtra(MESSAGE_KEY, song)
//            startService(intent)
//        }
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
    }

    private fun showProgressIndicator(loading:Boolean){
        progressIndicator.visibility= if(loading)  View.VISIBLE else View.INVISIBLE
    }

}