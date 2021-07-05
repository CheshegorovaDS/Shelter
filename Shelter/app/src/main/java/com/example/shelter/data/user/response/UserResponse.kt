package com.example.shelter.data.user.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("phone")
    val phone:String = "",
    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("patronymic")
    val patronymic: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("photo")
    val photo: ByteArray? = null
)
