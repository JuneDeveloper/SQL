package com.example.sql

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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