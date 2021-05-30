package com.example.shelter.data.di

import com.example.shelter.data.animal_type.repository.AnimalTypeApi
import com.example.shelter.data.animal_type.repository.AnimalTypeRepository
import com.example.shelter.data.animal_type.repository.IAnimalTypeApi
import com.example.shelter.data.animal_type.repository.IAnimalTypeRepository
import com.example.shelter.data.category.repository.CategoryApi
import com.example.shelter.data.category.repository.CategoryRepository
import com.example.shelter.data.category.repository.ICategoryApi
import com.example.shelter.data.category.repository.ICategoryRepository
import com.example.shelter.data.news.repository.INewsApi
import com.example.shelter.data.news.repository.INewsRepository
import com.example.shelter.data.news.repository.NewsApi
import com.example.shelter.data.news.repository.NewsRepository
import com.example.shelter.data.user.repositry.IUserApi
import com.example.shelter.data.user.repositry.IUserRepository
import com.example.shelter.data.user.repositry.UserApi
import com.example.shelter.data.user.repositry.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class ApiBinds {
    @Binds
    abstract fun bindNewsRepository(repository: NewsRepository): INewsRepository

    @Binds
    abstract fun bindNewsApi(newsApi: NewsApi): INewsApi

    @Binds
    abstract fun bindUserRepository(repository: UserRepository): IUserRepository

    @Binds
    abstract fun bindUserApi(userApi: UserApi): IUserApi

    @Binds
    abstract fun bindCategoryRepository(categoryRepository: CategoryRepository): ICategoryRepository

    @Binds
    abstract fun bindsCategoryApi(categoryApi: CategoryApi): ICategoryApi

    @Binds
    abstract fun bindAnimalTypeRepository(animalTypeRepository: AnimalTypeRepository): IAnimalTypeRepository

    @Binds
    abstract fun bindAnimalTypeApi(animalTypeApi: AnimalTypeApi): IAnimalTypeApi
}