package com.academy.a04_fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.academy.a04_fragments.fragments.FirstFragment
import com.academy.a04_fragments.fragments.SecondFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)

        btn1.setOnClickListener{
            val firstFragment = FirstFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer,firstFragment)
            transaction.commit()
        }

        btn2.setOnClickListener{
            val secondFragment = SecondFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, secondFragment)
            transaction.commit()
        }
    }
}