package com.example.shelter.presentation.creating_news.model

data class CreatingNewsState(
    val animalFormVisibility: Boolean = false,
    val addCardVisibility: Boolean = false,
    val progressBarVisibility: Boolean = true,
    val exceptionVisibility: Boolean = false
)