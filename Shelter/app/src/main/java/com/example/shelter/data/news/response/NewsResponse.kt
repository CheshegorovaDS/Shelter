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
    @SerializedName("idUser")
    val idUser: Int
)