package com.example.shelter.presentation.model

import com.example.shelter.presentation.about_animal.model.Sex
import com.example.shelter.presentation.about_animal.model.Sterilization

data class Animal (val name: String,
                   val photo: String,
                   val type: String,
                   val category: String,
                   val sex: Sex,
                   val breed: String,
                   val age: String,
                   val passport: String,
                   val sterilization: Sterilization,
                   val growth: String,
                   val description: String
)