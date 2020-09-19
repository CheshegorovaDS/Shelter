package com.example.shelter.presentation.model

import com.example.shelter.presentation.onBoarding.registration.model.TypeUser

data class User(
    val email: String,
    val password: String,
    val type: String = "",
    val phone:String = "",
    val city: String = ""
)