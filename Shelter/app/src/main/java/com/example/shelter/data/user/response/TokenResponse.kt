package com.example.shelter.data.user.response

import com.google.gson.annotations.SerializedName

data class TokenResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("accessToken")
    val accessToken: String
)