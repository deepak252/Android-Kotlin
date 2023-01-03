package com.academy.a06a_serrvices_createstartedservice

import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder
import android.os.Message
import android.util.Log
import com.academy.a05c_thread_backgroundthread_class.Playlist

class MyDownloadService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("MyDownloadService","onCreate Called")
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val song =intent?.getStringExtra(MainActivity.MESSAGE_KEY)
        Log.d("MyDownloadService","onStartCommand Called")

        val myDownloadTask = MyDownloadTask()
        if(song!=null) {
            // separate AsyncTask execution for each song
            myDownloadTask.execute(song)
        }
//        return super.onStartCommand(intent, flags, startId)
        return Service.START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d("MyDownloadService","onBind Called")
        TODO("Return the communication channel to the service.")
    }

    override fun onDestroy() {
        Log.d("MyDownloadService","onDestroy Called")
        super.onDestroy()
    }

    class MyDownloadTask : AsyncTask<String, String,String>(){
        override fun doInBackground(vararg songs: String?): String {
            for(song in songs){
                Log.d("MyDownloadTask","onProgressUpdate, Downloading song - $song")
                Thread.sleep(4000)

            }
            return "All songs have been downloaded"
        }

        override fun onProgressUpdate(vararg values: String?) {
            Log.d("MyDownloadTask","onProgressUpdate, Song Downloaded - ${values[0]}")
        }
        override fun onPostExecute(result: String?) {
            Log.d("MyDownloadTask","onPostExecute, $result")
        }
    }

}