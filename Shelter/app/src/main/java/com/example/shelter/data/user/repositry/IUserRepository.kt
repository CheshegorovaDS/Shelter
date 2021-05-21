package com.example.shelter.data.user.repositry

import com.example.shelter.presentation.model.User
import io.reactivex.Single

interface IUserRepository {
    fun getUserById(id: Int): Single<User>
}