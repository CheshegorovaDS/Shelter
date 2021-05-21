package com.example.shelter.presentation.model

data class News (
    val id: Int,
    val name: String,
    val photo: String,
    val category: String,
    val idUser: Int,
    val age: Int = 0,
    val sex: String? = null,
    val breed: String? = null,
    val passport: String? = null,
    val description: String? = null,
    val user: User? = null
)
