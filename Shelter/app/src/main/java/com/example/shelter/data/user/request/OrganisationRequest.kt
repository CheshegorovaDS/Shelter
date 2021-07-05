package com.example.shelter.data.user.request

import com.google.gson.annotations.SerializedName

data class OrganisationRequest(
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
    @SerializedName("title")
    val title: String
)