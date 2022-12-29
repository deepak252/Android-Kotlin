package com.academy.a05e_threads_create_asynctask

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

open class AsyncFragment : Fragment() {
    private var mTaskHandler : MyTaskHandler?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true   // To keep the instance of Fragment in Memory

    }
    fun runTask(vararg  args : String){
        val mTask= MyTask()
        mTask.execute(*args)
    }
    interface MyTaskHandler{
        fun handleTask(message : String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MyTaskHandler){
            mTaskHandler = context
        }
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
            mTaskHandler?.handleTask("${values[0]}")

        }

        override fun onPostExecute(result: String?) {
            mTaskHandler?.handleTask("$result")
        }

    }
}