package com.academy.a02_qouteshareapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var btnQuote:Button;
    lateinit var tvQuote:TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnQuote = findViewById<Button>(R.id.btnQuote);
        tvQuote = findViewById<TextView>(R.id.tvQuote);

        btnQuote.setOnClickListener(){
            val intent = Intent();
            intent.type ="text/plain";
            intent.action = Intent.ACTION_SEND;
            intent.putExtra(Intent.EXTRA_TEXT,tvQuote.text.toString());
            startActivity(intent)

        }

    }
}