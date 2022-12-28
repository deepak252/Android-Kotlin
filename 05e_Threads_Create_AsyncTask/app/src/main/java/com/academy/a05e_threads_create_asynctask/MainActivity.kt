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

        val myTask = MyTask(this)
        myTask.execute("Red", "Green", "Blue")
//        myTask.execute("Red", "Green", "Blue")
        val myTask2 = MyTask(this)
        myTask2.execute("Red", "Green")

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

    class MyTask(val mainActivity: MainActivity) : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg args: String?): String {
            for(arg in args){
                publishProgress(arg)
                Log.d("doInBackground", "arg = $arg")
                Thread.sleep(2000)
            }
            return ""
        }

        override fun onProgressUpdate(vararg values: String?) {
            mainActivity.logMessage("\n${values[0]}")
        }

    }
}