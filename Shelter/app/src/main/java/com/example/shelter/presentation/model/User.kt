package com.example.shelter.presentation.model

data class User(
    val id: Int,
    val email: String,
    val password: String = "",
    val type: String = "",
    val phone:String = "",
    val city: String = "",
    val country: String = "",
    val photo: String = "",
    val human: Human? = null,
    val organisation: Organisation? = null
)