package com.example.shelter.presentation.model

import com.example.shelter.presentation.about_animal.model.Sex
import com.example.shelter.presentation.about_animal.model.Sterilization

data class Animal (
    val name: String,
    val photo: String,
    val type: String = "",
    val sex: Sex = Sex.NONE,
    val breed: String = "",
    val age: Int = 0,
    val passport: String = "",
    val description: String = ""
)