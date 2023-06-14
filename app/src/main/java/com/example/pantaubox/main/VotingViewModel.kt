package com.example.pantaubox.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pantaubox.di.Repository
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class VotingViewModel(private val pref: Repository) : ViewModel() {

    fun votingPaslon(nik: String) {
        viewModelScope.launch {
            pref.voteUser(nik)
        }
    }

    fun tokenState(): LiveData<String> {
        return pref.getToken()
    }
}