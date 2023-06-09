package com.example.pantaubox.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.pantaubox.R
import com.example.pantaubox.login.LoginWelcome
import com.example.pantaubox.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this, LoginWelcome::class.java))
                finish()
            },
            SPLASH_TIME_OUT
        )
    }
}