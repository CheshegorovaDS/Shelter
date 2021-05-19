package com.example.shelter.presentation.about_animal.model

data class AboutAnimalState (
    val progressbarVisibility: Boolean = true,
    val nameVisibility: Boolean = false,
    val photoVisibility: Boolean = false,
    val ageVisibility: Boolean = false,
    val breedVisibility: Boolean = false,
    val animalType: Boolean = false,
    val sexVisibility: Boolean = false,
    val categoryVisibility: Boolean = false,
    val passportVisibility: Boolean = false,
    val descriptionVisibility: Boolean = false,
    val userVisibility: Boolean = false
)