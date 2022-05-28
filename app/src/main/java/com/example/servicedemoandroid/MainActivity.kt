package com.example.servicedemoandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.R
import startedService.MyOwnService

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSomething(view: View) {
        val intent = Intent(this, MyOwnService::class.java)
        startService(intent)
    }

    fun stopSomething(view: View) {
        val intent = Intent(this, MyOwnService::class.java)
        stopService(intent)
    }


}