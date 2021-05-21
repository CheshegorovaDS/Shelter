package com.example.shelter.data.user.repositry

import com.example.shelter.presentation.model.User
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(
    private var userApi: IUserApi
) : IUserRepository {

    override fun getUserById(id: Int): Single<User> {
        return userApi.getUserById(id)
    }
}