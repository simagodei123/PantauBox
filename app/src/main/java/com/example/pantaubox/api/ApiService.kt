package com.example.pantaubox.api

import com.example.pantaubox.response.LoginResponse
import com.example.pantaubox.response.VotingResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Multipart
    @POST("login")
    fun loginUser(
        @Part("nik") nik: RequestBody,
    ): Call<LoginResponse>

    @PUT("vote/{nik}")
    fun voteUser(
        @Path("nik") nik: String,
    ): Call<VotingResponse>
}