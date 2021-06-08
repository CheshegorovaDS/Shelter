package com.example.shelter.data.user.repositry

import com.example.shelter.data.user.request.TokenRequest
import com.example.shelter.data.user.request.UserRequest
import com.example.shelter.presentation.model.User
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(
    private var userApi: IUserApi
) : IUserRepository {

    override fun login(login: String, password: String): Single<Pair<Int, String>> {
        return userApi.login(TokenRequest(login, password)).map {
            Pair(it.id, it.accessToken)
        }
    }

    override fun getUserById(id: Int): Single<User> {
        return userApi.getUserById(id)
    }

    override fun logout(token: String): Observable<Boolean> {
        return userApi.logout(token)
    }
}