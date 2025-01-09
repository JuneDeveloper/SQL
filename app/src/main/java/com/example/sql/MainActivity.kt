package com.example.sql

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var beginButtonBTN:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beginButtonBTN = findViewById(R.id.beginButtonBTN)

        beginButtonBTN.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}