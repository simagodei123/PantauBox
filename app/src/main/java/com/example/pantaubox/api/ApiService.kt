package com.example.pantaubox.api

import com.example.pantaubox.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("nik") nik: String,
    ): Call<LoginResponse>
}