package com.example.shelter.data.user.repositry

import com.example.shelter.presentation.model.User
import io.reactivex.Observable
import io.reactivex.Single

interface IUserRepository {
    fun login(login: String, password: String): Single<Pair<Int, String>>
    fun getUserById(id: Int): Single<User>
    fun logout(token: String): Observable<Boolean>
}