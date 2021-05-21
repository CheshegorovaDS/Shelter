package com.example.shelter.data.user.repositry

import com.example.shelter.data.user.request.UserRequest
import com.example.shelter.data.user.response.UserResponse
import com.example.shelter.presentation.model.User
import io.reactivex.Observable
import io.reactivex.Single

interface IUserApi {
    fun login(request: UserRequest): Observable<UserResponse>
    fun registration(request: UserRequest): Observable<UserResponse>
    fun getUserById(id: Int): Single<User>
}