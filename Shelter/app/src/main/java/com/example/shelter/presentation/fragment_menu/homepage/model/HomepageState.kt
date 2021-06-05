package com.example.shelter.presentation.fragment_menu.homepage.model

data class HomepageState (
    val progressbarVisibility: Boolean = true,
    val userInfoVisibility: Boolean = false,
    val listNewsVisibility: Boolean = false,
    val listIsEmptyText: Boolean = false
)