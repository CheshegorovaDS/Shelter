package com.example.shelter.data.user.repositry

import com.example.shelter.data.user.request.UserRequest
import com.example.shelter.data.user.response.UserResponse
import io.reactivex.Observable

class UserApi: IUserApi {
    override fun login(request: UserRequest): Observable<UserResponse> {
        TODO("Not yet implemented")
    }

    override fun registration(request: UserRequest): Observable<UserResponse> {
        TODO("Not yet implemented")
    }

}
