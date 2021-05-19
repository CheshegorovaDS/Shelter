package com.example.shelter.presentation.model

data class User(
    val id: Int,
    val email: String,
    val password: String,
    val type: String = "",
    val phone:String = "",
    val city: String = ""
)