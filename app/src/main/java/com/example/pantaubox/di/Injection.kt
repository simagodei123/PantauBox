package com.example.pantaubox.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.pantaubox.api.ApiConfig

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("token")

object Injection {
    fun provideRepository(context: Context): Repository {
        val preferences = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.ApiService()
        return Repository.getInstance(preferences, apiService)
    }
}