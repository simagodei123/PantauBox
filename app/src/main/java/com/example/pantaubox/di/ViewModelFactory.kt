package com.example.pantaubox.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pantaubox.login.LoginViewModel
import com.example.pantaubox.login.reco.RegisFotoViewModel
import com.example.pantaubox.main.MainViewModel
import com.example.pantaubox.main.VotingViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val pref: Repository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(pref) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(pref) as T
            }
            modelClass.isAssignableFrom(RegisFotoViewModel::class.java) -> {
                RegisFotoViewModel(pref) as T
            }
            modelClass.isAssignableFrom(VotingViewModel::class.java) -> {
                VotingViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
        }
    }
}