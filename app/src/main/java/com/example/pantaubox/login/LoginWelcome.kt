package com.example.pantaubox.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pantaubox.databinding.ActivityLoginWelcomeBinding
import com.example.pantaubox.login.reco.RegisNIK

class LoginWelcome : AppCompatActivity() {
    private lateinit var binding: ActivityLoginWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVerif1.setOnClickListener {
            startIntent()
        }
    }

    private fun startIntent() {
        startActivity(Intent(this@LoginWelcome, RegisNIK::class.java))
        finish()
    }
}