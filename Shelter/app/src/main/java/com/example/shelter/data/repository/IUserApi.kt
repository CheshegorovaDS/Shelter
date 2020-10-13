package com.example.shelter.data.repository

import com.example.shelter.data.request.UserRequest
import com.example.shelter.data.response.UserResponse
import io.reactivex.Observable

interface IUserApi {
    fun login(request: UserRequest): Observable<UserResponse>
    fun registration(request: UserRequest): Observable<UserResponse>
}