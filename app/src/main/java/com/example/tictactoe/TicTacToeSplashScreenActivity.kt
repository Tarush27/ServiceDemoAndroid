package com.example.tictactoe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.ActivityTicTacToeSplashScreenBinding

@SuppressLint("CustomSplashScreen")
class TicTacToeSplashScreenActivity : AppCompatActivity() {

    private lateinit var ticTacToeSplashScreenBinding: ActivityTicTacToeSplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ticTacToeSplashScreenBinding = ActivityTicTacToeSplashScreenBinding.inflate(layoutInflater)
        setContentView(ticTacToeSplashScreenBinding.root)

        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.tic_tac_toe_animation)
        ticTacToeSplashScreenBinding.ticTacToeIcon.startAnimation(slideAnimation)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, TicTacToeGameMainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}