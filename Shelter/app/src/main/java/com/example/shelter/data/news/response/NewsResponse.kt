package com.example.shelter.data.news.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("idAnimal")
    val id: Int,
    @SerializedName("nameAnimal")
    val name: String,
    @SerializedName("photoAnimal")
    val photo: String,
    @SerializedName("titleCategory")
    val category: String,
    @SerializedName("ageAnimal")
    val age: Int,
    @SerializedName("breedAnimal")
    val breed: String?,
    @SerializedName("sexAnimal")
    val sex: String,
    @SerializedName("passportAnimal")
    val passport: String?,
    @SerializedName("descriptionAnimal")
    val description: String?,
    @SerializedName("idUser")
    val idUser: Int,
    @SerializedName("phoneUser")
    val phone: String,
    @SerializedName("emailUser")
    val email: String
    )