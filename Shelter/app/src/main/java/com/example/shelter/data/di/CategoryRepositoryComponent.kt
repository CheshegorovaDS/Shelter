package com.example.shelter.data.di

import com.example.shelter.data.category.repository.ICategoryApi
import com.example.shelter.data.category.repository.ICategoryRepository
import dagger.Component

@Component(modules = [ApiBinds::class])
interface CategoryRepositoryComponent {
    fun getCategoryRepository(): ICategoryRepository
    fun getCategoryApi(): ICategoryApi
}
