package com.example.pantaubox.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pantaubox.di.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val pref: Repository) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }
}