package com.example.shelter.presentation.fragment_menu.news.model

data class FilterNews(
    val listCategoriesId: List<Int> = listOf(),
    val listTypesId: List<Int> = listOf(),
    val request: String? = null
)