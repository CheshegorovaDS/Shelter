package com.example.shelter.data.repository

import com.example.shelter.data.request.UserRequest
import com.example.shelter.data.response.UserResponse
import io.reactivex.Observable

class UserApi: IUserApi {
    override fun login(request: UserRequest): Observable<UserResponse> {
        TODO("Not yet implemented")
    }

    override fun registration(request: UserRequest): Observable<UserResponse> {
        TODO("Not yet implemented")
    }

}
