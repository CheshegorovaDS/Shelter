package com.example.shelter.data.category.repository

import com.example.shelter.presentation.model.Category
import io.reactivex.Single

interface ICategoryApi {
    fun getAllCategories(): Single<List<Category>>
}