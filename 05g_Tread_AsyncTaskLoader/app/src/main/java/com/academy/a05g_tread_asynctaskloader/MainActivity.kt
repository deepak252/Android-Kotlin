package com.academy.a05g_tread_asynctaskloader


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.loader.app.LoaderManager
import androidx.loader.content.AsyncTaskLoader
import androidx.loader.content.Loader

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {
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
//        showProgressIndicator(true)
        // Will trigger "onCreateLoader" method
        supportLoaderManager.initLoader(1000,null,this).forceLoad()

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

    class MyTaskLoader( context : Context) : AsyncTaskLoader<String>(context) {
        override fun loadInBackground(): String? {
            Log.d("MyTaskLoader", "loadInBackground : Thread started - ${Thread.currentThread().name}")
            Thread.sleep(4000)
            Log.d("MyTaskLoader", "loadInBackground : Thread completed - ${Thread.currentThread().name}")
            return null;
        }

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        return MyTaskLoader(this)
    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {

    }

    override fun onLoaderReset(loader: Loader<String>) {

    }
}