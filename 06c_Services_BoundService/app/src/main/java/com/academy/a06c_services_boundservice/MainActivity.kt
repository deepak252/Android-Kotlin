package com.academy.a06c_services_boundservice


import android.content.*
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
    private lateinit var btnPlayer : Button
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

    val mReceiver = object : BroadcastReceiver(){
        override fun onReceive(ctx: Context?, intent: Intent?) {
            val msg = intent?.getStringExtra(MESSAGE_KEY)
            if(msg=="done"){
                btnPlayer.text = "PLAY"
            }
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
        LocalBroadcastManager.getInstance(applicationContext).registerReceiver(mReceiver,
            IntentFilter(MusicPlayerService.MUSIC_COMPLETE)
        )
    }

    override fun onStop() {
        super.onStop()
        if(mBound){
            unbindService(mServiceConnection)
            mBound=false
        }
        LocalBroadcastManager.getInstance(applicationContext).unregisterReceiver(mReceiver)
    }

    private fun initViews(){
        edtCode = findViewById(R.id.edtCode)
        btnRun = findViewById(R.id.btnRun)
        btnClear = findViewById(R.id.btnClear)
        btnPlayer = findViewById(R.id.btnPlayer)
        progressIndicator = findViewById(R.id.progressIndicator)
        showProgressIndicator(false)

//        edtCode.setText(R.string.lorem_ipsum)

        btnClear.setOnClickListener{
            clearCode()
        }

        btnRun.setOnClickListener{
            runCode()
        }
        btnPlayer.setOnClickListener{
            onBtnPlayerClicked()
        }
    }

    private fun onBtnPlayerClicked(){
        if(mBound){
            if(mMusicPlayerService?.isPlaying()==true){
                mMusicPlayerService!!.pause()
                btnPlayer.text = "PLAY"
            }else{
                mMusicPlayerService!!.play()
                btnPlayer.text = "PAUSE"
            }
        }
    }

    private fun runCode(){
        logMessage("\nRunning Code")
        showProgressIndicator(true)
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