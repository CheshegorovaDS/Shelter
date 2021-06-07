package com.example.shelter.presentation.model

import com.example.shelter.presentation.about_animal.model.Sex
import com.example.shelter.presentation.about_animal.model.Sterilization

data class Animal (
    val name: String,
    val photo: String,
    val type: AnimalType? = null,
    val sex: Sex = Sex.NONE,
    val category: Category? = null,
    val breed: String? = null,
    val age: String? = null,
    val passport: String? = null,
    val description: String? = null
)