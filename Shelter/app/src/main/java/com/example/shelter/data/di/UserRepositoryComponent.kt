package com.example.shelter.data.di

import com.example.shelter.data.user.repositry.IUserApi
import com.example.shelter.data.user.repositry.IUserRepository
import dagger.Component

@Component(modules = [ApiBinds::class])
interface UserRepositoryComponent {
    fun getUserRepository(): IUserRepository
    fun getUserApi(): IUserApi
}