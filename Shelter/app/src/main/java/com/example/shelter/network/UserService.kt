package com.example.shelter.network

import com.example.shelter.data.user.response.TokenResponse
import com.example.shelter.data.user.response.UserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("/api/human/{id}")
    fun getHumanById(@Path("id")id: Int): Single<Response<UserResponse>>


    @GET("/api/organisation/{id}")
    fun getOrganisationById(@Path("id")id: Int): Single<Response<UserResponse>>

    @GET("/api/login/login={login}&pass={password}")
    fun login(
        @Path("login")login: String,
        @Path("password")password: String
    ): Single<Response<TokenResponse>>
}