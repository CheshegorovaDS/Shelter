package com.example.shelter.presentation.fragment_menu.news.model

data class NewsState(
    val progressBarVisibility: Boolean = false,
    val listVisibility: Boolean = false,
    val filterVisibility: Boolean = true,
    val searchVisibility: Boolean = true,
    val backVisibility: Boolean = false,
    val enterVisibility: Boolean = false,
    val addNewsEnabled: Boolean = false,
    val searchStringClear: Boolean = false
)