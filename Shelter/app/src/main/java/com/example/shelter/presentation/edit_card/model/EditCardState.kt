package com.example.shelter.presentation.edit_card.model

data class EditCardState(
    val cardFieldsVisibility: Boolean = false,
    val progressBarVisibility: Boolean = true,
    val exceptionVisibility: Boolean = false,
    val applyVisibility: Boolean = false,
    val applyEnabled: Boolean = false
)