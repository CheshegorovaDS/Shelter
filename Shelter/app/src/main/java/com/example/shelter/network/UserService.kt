package com.example.shelter.network

import com.example.shelter.data.user.request.HumanRequest
import com.example.shelter.data.user.request.OrganisationRequest
import com.example.shelter.data.user.response.TokenResponse
import com.example.shelter.data.user.response.UserResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

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

    @PUT("/api/human/{id}")
    fun editHuman(@Body request: HumanRequest, @Path("id")id: Int): Observable<Response<Any>>

    @DELETE("/api/logout/uuid={uuid}")
    fun logout(@Path("uuid") uuid: String): Observable<Response<Any>>

    @POST("/api/human")
    fun registerHuman(@Body request: HumanRequest): Observable<Response<Any>>

    @POST("/api/organisation")
    fun registerOrganisation(@Body request: OrganisationRequest): Observable<Response<Any>>

}