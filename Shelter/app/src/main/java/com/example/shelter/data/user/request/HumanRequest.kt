package com.example.shelter.data.user.request

import com.google.gson.annotations.SerializedName

data class HumanRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("patronymic")
    val patronymic: String? = null,
    @SerializedName("photo")
    val photo: ByteArray? = null
)