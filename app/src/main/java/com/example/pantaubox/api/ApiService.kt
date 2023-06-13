package com.example.pantaubox.api

import com.example.pantaubox.response.LoginResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    //@FormUrlEncoded
    //@POST("login")
    //fun loginUser(
    //    @Field("nik") nik: String,
   // ): Call<LoginResponse>

    @Multipart
    @POST("login")
    fun loginUser(
        @Part("nik") nik : RequestBody,
    ): Call<LoginResponse>
}