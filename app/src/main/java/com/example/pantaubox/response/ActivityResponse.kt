package com.example.pantaubox.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("token")
    val token: String? = null,
)

data class UploadResponse(
    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("imageUrl")
    val imageUrl: String? = null,
)

data class VotingResponse(
    @field:SerializedName("success")
    val success: Boolean? = null,
)