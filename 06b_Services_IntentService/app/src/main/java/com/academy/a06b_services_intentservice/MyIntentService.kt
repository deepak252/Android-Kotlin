package com.academy.a06b_services_intentservice

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log


class MyIntentService : IntentService("MyIntentService") {

    override fun onCreate() {
        super.onCreate()
        Log.d("onCreate","Thread Name = ${Thread.currentThread().name}")
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d("onHandleIntent","Thread Name = ${Thread.currentThread().name}")
        val song = intent?.getStringExtra(MainActivity.MESSAGE_KEY)
        if(song!=null){
            downloadSong(song)
        }
//        when (intent?.action) {
//            ACTION_FOO->  {
//                val param1 = intent.getStringExtra(EXTRA_PARAM1)
//                val param2 = intent.getStringExtra(EXTRA_PARAM2)
//                handleActionFoo(param1, param2)
//            }
//            ACTION_BAZ -> {
//                val param1 = intent.getStringExtra(EXTRA_PARAM1)
//                val param2 = intent.getStringExtra(EXTRA_PARAM2)
//                handleActionBaz(param1, param2)
//            }
//        }
    }

    override fun onDestroy() {
        super.onCreate()
        Log.d("onDestroy","Thread Name = ${Thread.currentThread().name}")
    }

    private fun downloadSong( song : String){
        Log.d("downloadSong", "Starting Download - $song")
        Thread.sleep(4000)
        Log.d("downloadSong", "Download Complete - $song")
    }

//    private fun handleActionFoo(param1: String?, param2: String?) {
//        TODO("Handle action Foo")
//    }
//
//
//    private fun handleActionBaz(param1: String?, param2: String?) {
//        TODO("Handle action Baz")
//    }
//
//    companion object {
//        @JvmStatic
//        fun startActionFoo(context: Context, param1: String, param2: String) {
//            val intent = Intent(context, MyIntentService::class.java).apply {
//                action = ACTION_FOO
//                putExtra(EXTRA_PARAM1, param1)
//                putExtra(EXTRA_PARAM2, param2)
//            }
//            context.startService(intent)
//        }
//
//        @JvmStatic
//        fun startActionBaz(context: Context, param1: String, param2: String) {
//            val intent = Intent(context, MyIntentService::class.java).apply {
//                action = ACTION_BAZ
//                putExtra(EXTRA_PARAM1, param1)
//                putExtra(EXTRA_PARAM2, param2)
//            }
//            context.startService(intent)
//        }
//    }


}