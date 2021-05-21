package com.example.shelter.data.di

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
}