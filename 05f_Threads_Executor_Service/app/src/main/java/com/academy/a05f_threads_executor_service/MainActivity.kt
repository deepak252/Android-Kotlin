package com.academy.a05f_threads_executor_service


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(){
    private lateinit var edtCode : EditText
    private lateinit var btnRun : Button
    private lateinit var btnClear : Button
    private lateinit var progressIndicator : ProgressBar
    private var mExecuter : ExecutorService?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        mExecuter = Executors.newFixedThreadPool(4)

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

        for (i in 1..10){
            val work = Work(i)
            mExecuter?.execute(work)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        mExecuter?.shutdown()
    }


    fun logMessage(message:String){
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