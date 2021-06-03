package com.example.shelter.presentation.filtering_news.model

data class FilteringNewsState(
    val progressBarVisibility: Boolean = true,
    val categoriesVisibility: Boolean = false,
    val animalTypesVisibility: Boolean = false,
    val applyVisibility: Boolean = false,
    val checkedCategoriesVisibility: Boolean = false,
    val checkedAnimalTypesVisibility: Boolean = false
)