package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private val startBtn: Button = findViewById(R.id.btnStart)
    private val stopBtn: Button = findViewById(R.id.btnStop)
    private val getRandomNoBtn: Button = findViewById(R.id.randomNoBtn)
    private val showRandomNoTextView: TextView = findViewById(R.id.showRandomTextView)
    private val isRunning: Nothing = TODO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        startBtn.setOnClickListener {
            val intent = Intent(this, MyBoundService::class.java)
        }
    }
}