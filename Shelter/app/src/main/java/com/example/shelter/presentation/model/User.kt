package com.example.shelter.presentation.model

data class User(
    val email: String,
    val password: String,
    val type: String = "",
    val phone:String = "",
    val city: String = ""
)