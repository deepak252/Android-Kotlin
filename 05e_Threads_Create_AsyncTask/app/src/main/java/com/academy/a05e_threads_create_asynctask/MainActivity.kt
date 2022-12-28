package com.academy.a05e_threads_create_asynctask


import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    private var mTask : MyTask?=null
    private var mTaskRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()


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

        if(mTaskRunning && mTask!=null){
            mTask!!.cancel(true)
            mTaskRunning=false
        }else{
            mTask= MyTask()
            mTask!!.execute("Red", "Green", "Blue")
            mTaskRunning=true
        }

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

    inner class MyTask() : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg args: String?): String {
            for(arg in args){
                if(isCancelled){
                    publishProgress("Task Cancelled")
                    break
                }
                publishProgress(arg)
                Log.d("doInBackground", "arg = $arg")
                Thread.sleep(2000)
            }
            return "Download Complete"
        }
        //Not called on "Task Cancelled"
        override fun onProgressUpdate(vararg values: String?) {
            logMessage("\n${values[0]}")

        }

        override fun onPostExecute(result: String?) {
            logMessage("\n $result")
            showProgressIndicator(false)
        }

        override fun onCancelled() {
            logMessage("\n Task has been Cancelled")
            showProgressIndicator(false)
        }

        override fun onCancelled(result: String?) {
            logMessage("\n Cancelled With Returned Data : $result")
            showProgressIndicator(false)
        }

    }
}