package com.example.pantaubox.login.reco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.pantaubox.R
import com.example.pantaubox.databinding.ActivityRegisNikBinding
import com.example.pantaubox.di.ViewModelFactory
import com.example.pantaubox.login.LoginViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class RegisNIK : AppCompatActivity() {

    private lateinit var viewModelFactory: ViewModelFactory
    private val loginViewModel: LoginViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ActivityRegisNikBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisNikBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        viewModelFactory = ViewModelFactory.getInstance(this)

        loginViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        loginViewModel.tokenState().observe(this){
            if (it != "") {
                showLoading(false)
                startActivity(Intent(this@RegisNIK, RegisFoto::class.java))
                finish()
            }
        }
    }

    private fun setupAction() {
        binding.btnGoVerif.setOnClickListener {

            val nik = binding.edLoginNik.text.toString()
            val mediaType = "application/form-data".toMediaType()
            val NIK = nik.toRequestBody(mediaType)

            when {
                nik.length < 16 -> {
                    binding.edLoginNik.error = getString(R.string.required_nik)
                }
                else -> {
                    loginViewModel.loginUser(NIK, this)
                    loginViewModel.token.observe(this) {
                        if (it != "") {
                            if (it != null) {
                                loginViewModel.saveToken(it)
                            }
                        }
                    }
                    startIntent()
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbLogin.visibility = View.VISIBLE
        } else {
            binding.pbLogin.visibility = View.GONE
        }
    }
    private fun startIntent() {
        loginViewModel.isLogin.observe(this){ login ->
            showLoading(true)
            if (login) {
                //startActivity(Intent(this@RegisNIK, MainActivity::class.java)) //tunggu face reco
                startActivity(Intent(this@RegisNIK, RegisFoto::class.java)) //tunggu face reco
                finish()
                Toast.makeText(
                    this@RegisNIK,
                    R.string.login_success,
                    Toast.LENGTH_SHORT
                ).show()
                showLoading(false)
            }
            else if (!login) {
                Toast.makeText(
                    this@RegisNIK,
                    R.string.login_failed,
                    Toast.LENGTH_SHORT
                ).show()
                showLoading(false)
            }
            else {
                Toast.makeText(
                    this@RegisNIK,
                    R.string.server_caused,
                    Toast.LENGTH_SHORT
                ).show()
                showLoading(false)
            }
        }

    }
}