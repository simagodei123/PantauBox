package com.example.pantaubox.api

import com.example.pantaubox.response.LoginResponse
import com.example.pantaubox.response.UploadResponse
import com.example.pantaubox.response.VotingResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @Multipart
    @POST("login")
    fun loginUser(
        @Part("nik") nik : RequestBody,
    ): Call<LoginResponse>

    @POST("vote/{nik}")
    fun voteuser(
        @Path("nik") nik: String,
    ): Call<VotingResponse>
}