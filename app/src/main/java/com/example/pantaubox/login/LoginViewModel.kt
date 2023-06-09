package com.example.pantaubox.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pantaubox.di.Repository
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: Repository) : ViewModel() {
    val isLogin: LiveData<Boolean> = pref.isLogin
    val isLoading: LiveData<Boolean> = pref.isLoading
    val token: LiveData<String?> = pref.mToken

    fun loginUser(nik: String, context: Context) {
        viewModelScope.launch {
            pref.loginUser(nik, context)
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            pref.saveToken(token)
        }
    }
}