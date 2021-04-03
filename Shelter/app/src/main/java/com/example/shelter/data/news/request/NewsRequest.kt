package com.example.shelter.data.news.request

import com.google.gson.annotations.SerializedName

data class NewsRequest (
    @SerializedName(value = "id")
    val id: Long = 0L,
    @SerializedName(value = "name")
    val name: String = ""
)