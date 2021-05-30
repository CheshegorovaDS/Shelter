package com.example.shelter.data.category.repository

import com.example.shelter.presentation.model.Category
import io.reactivex.Single

interface ICategoryRepository {
    fun getCategoryList(): Single<List<Category>>
}