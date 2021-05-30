package com.example.shelter.data.animal_type.response

import com.google.gson.annotations.SerializedName

data class AnimalTypeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)