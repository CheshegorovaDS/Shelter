package com.example.shelter.data.user.repositry

import com.example.shelter.data.user.request.TokenRequest
import com.example.shelter.data.user.request.UserRequest
import com.example.shelter.data.user.response.TokenResponse
import com.example.shelter.data.user.response.UserResponse
import com.example.shelter.presentation.model.User
import io.reactivex.Observable
import io.reactivex.Single

interface IUserApi {
    fun login(request: TokenRequest): Single<TokenResponse>
    fun registration(request: UserRequest): Observable<UserResponse>
    fun getUserById(id: Int): Single<User>
    fun logout(token: String): Observable<Boolean>
}