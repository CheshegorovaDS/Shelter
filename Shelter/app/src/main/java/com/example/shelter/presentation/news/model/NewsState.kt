package com.example.shelter.presentation.news.model

data class NewsState(
    val progressBarVisibility: Boolean = false,
    val filterVisibility: Boolean = true,
    val searchVisibility: Boolean = false,
    val backVisibility: Boolean = false,
    val addNewsEnabled: Boolean = false
)