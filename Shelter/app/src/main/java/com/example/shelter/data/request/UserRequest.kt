package com.example.shelter.data.request

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("email")
    val email: String = "",
    @SerializedName("password")
    val password: String = "")
