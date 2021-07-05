package com.example.shelter.data.user.repositry

import com.example.shelter.data.user.request.HumanRequest
import com.example.shelter.data.user.request.OrganisationRequest
import com.example.shelter.data.user.request.TokenRequest
import com.example.shelter.data.user.request.UserRequest
import com.example.shelter.data.user.response.TokenResponse
import com.example.shelter.data.user.response.UserResponse
import com.example.shelter.presentation.model.User
import io.reactivex.Observable
import io.reactivex.Single

interface IUserApi {
    fun login(request: TokenRequest): Single<TokenResponse>
    fun registrationHuman(request: HumanRequest): Observable<Boolean>
    fun registrationOrganisation(request: OrganisationRequest): Observable<Boolean>
    fun editHuman(request: HumanRequest): Observable<Boolean>
    fun getUserById(id: Int): Single<User>
    fun logout(token: String): Observable<Boolean>
}