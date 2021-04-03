package com.example.shelter.data.user.repositry

import com.example.shelter.data.user.request.UserRequest
import com.example.shelter.data.user.response.UserResponse
import io.reactivex.Observable

interface IUserApi {
    fun login(request: UserRequest): Observable<UserResponse>
    fun registration(request: UserRequest): Observable<UserResponse>
}