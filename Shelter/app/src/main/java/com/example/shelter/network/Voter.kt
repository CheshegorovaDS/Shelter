package com.example.shelter.network

import com.google.gson.annotations.SerializedName

data class Voter (
    @SerializedName("id")
    val id: Int,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("patronymic")
    val patronymic: String?,
    @SerializedName("birthdate")
    val birthdate: String,
    @SerializedName("passport")
    val passport: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("house")
    val house: String,
    @SerializedName("flat")
    val flat: String
)