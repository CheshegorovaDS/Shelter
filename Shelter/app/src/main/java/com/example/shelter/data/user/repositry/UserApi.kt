package com.example.shelter.data.user.repositry

import com.example.shelter.data.user.request.TokenRequest
import com.example.shelter.data.user.request.UserRequest
import com.example.shelter.data.user.response.TokenResponse
import com.example.shelter.data.user.response.UserResponse
import com.example.shelter.network.NetworkService
import com.example.shelter.presentation.model.Human
import com.example.shelter.presentation.model.Organisation
import com.example.shelter.presentation.model.User
import io.reactivex.Observable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class UserApi @Inject constructor(): IUserApi {
    val builder = NetworkService
    private val service = builder.buildUserService()

    override fun login(request: TokenRequest): Single<TokenResponse> {
        return service.login(request.login, request.password).map {
            if (it.isSuccessful){
                it.body()
            } else {
                throw Exception("fail")
            }
        }
    }

    override fun registration(request: UserRequest): Observable<UserResponse> {
        TODO("Not yet implemented")
    }

    override fun getUserById(id: Int): Single<User> {
        return getHumanById(id)
    }

    override fun logout(token: String): Observable<Boolean> {
        return service.logout(token).map {
            if (it.isSuccessful) {
                it.isSuccessful
            } else {
                throw Exception("fail")
            }
        }
    }

    private fun getHumanById(id: Int): Single<User> {
        return service.getHumanById(id).map {
            if (it.isSuccessful) {
                User(
                    id = it.body()!!.id,
                    email = it.body()!!.email,
                    phone = it.body()!!.phone,
                    password = it.body()!!.password,
                    human = Human(
                        firstName = it.body()!!.firstName,
                        lastName = it.body()!!.lastName,
                        patronymic = it.body()!!.patronymic
                    )
                )
            } else {
                getOrganisationById(id).blockingGet()
            }
        }
    }

    private fun getOrganisationById(id: Int): Single<User> {
        return service.getOrganisationById(id).map {
            if (it.isSuccessful) {
                User(
                    id = it.body()!!.id,
                    email = it.body()!!.email,
                    phone = it.body()!!.phone,
                    password = it.body()!!.password,
                    organisation = Organisation(
                        title = it.body()!!.title!!
                    )
                )
            } else {
                throw Exception("fail")
            }
        }
    }

}
