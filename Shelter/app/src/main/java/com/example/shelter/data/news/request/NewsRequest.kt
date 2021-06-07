package com.example.shelter.data.news.request

import com.google.gson.annotations.SerializedName

data class NewsRequest (
    @SerializedName("idUser")
    val idUser: Int,
    @SerializedName("idCategory")
    val idCategory: Int,
    @SerializedName("nameAnimal")
    val nameAnimal: String,
    @SerializedName("photoAnimal")
    val photoAnimal: String,
    @SerializedName("ageAnimal")
    val ageAnimal: Int?,
    @SerializedName("breedAnimal")
    val breedAnimal: String?,
    @SerializedName("idAnimalType")
    val idAnimalType: Int,
    @SerializedName("sexAnimal")
    val sexAnimal: String,
    @SerializedName("passportAnimal")
    val passportAnimal: String?,
    @SerializedName("descriptionAnimal")
    val descriptionAnimal: String?
)