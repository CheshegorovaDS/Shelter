package com.example.shelter.data.di

import com.example.shelter.data.news.repository.INewsApi
import com.example.shelter.data.news.repository.INewsRepository
import dagger.Component

@Component(modules = [ApiBinds::class])
interface NewsRepositoryComponent {
    fun getNewsRepository(): INewsRepository
    fun getNewsApi(): INewsApi
}