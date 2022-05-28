package com.example.servicedemoandroid

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import boundService.MyBoundService
import com.example.tictactoe.R

class MainActivity2 : AppCompatActivity() {

    private lateinit var myBoundService: MyBoundService
    private val startBtn: Button
        get() = findViewById(R.id.btnStart)
    private val stopBtn: Button get() = findViewById(R.id.btnStop)
    private val getRandomNoBtn: Button get() = findViewById(R.id.randomNoBtn)
    private val showRandomNoTextView: TextView get() = findViewById(R.id.showRandomTextView)
    private var isRunning: Boolean = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val myBinder = p1 as MyBoundService.MyBinder
            myBoundService = myBinder.getService()
            isRunning = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isRunning = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        startBtn.setOnClickListener {
            val intent = Intent(this, MyBoundService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        stopBtn.setOnClickListener {
            unbindService(connection)
            isRunning = false
        }

        getRandomNoBtn.setOnClickListener {
            if (isRunning) {
                showRandomNoTextView.text = myBoundService.generateIntegerRandomNo().toString()
            }
        }
    }
}

