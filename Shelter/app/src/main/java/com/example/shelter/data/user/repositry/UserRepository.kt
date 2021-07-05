package com.example.shelter.data.user.repositry

import com.example.shelter.data.user.request.HumanRequest
import com.example.shelter.data.user.request.OrganisationRequest
import com.example.shelter.data.user.request.TokenRequest
import com.example.shelter.data.user.request.UserRequest
import com.example.shelter.presentation.model.User
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(
    private var userApi: IUserApi
) : IUserRepository {

    override fun login(login: String, password: String): Single<Pair<Int, String>> {
        return userApi.login(TokenRequest(login, password)).map {
            Pair(it.id, it.accessToken)
        }
    }

    override fun register(user: User): Observable<Boolean> {
        return if (user.human != null) {
            userApi.registrationHuman(
                HumanRequest(
                    id = user.id,
                    email = user.email,
                    phone = user.phone,
                    city = user.city,
                    password = user.password,
                    country = user.country,
                    firstName = user.human.firstName!!,
                    lastName = user.human.lastName!!,
                    patronymic = user.human.patronymic
                )
            )
        } else {
            userApi.registrationOrganisation(
                OrganisationRequest(
                    id = user.id,
                    email = user.email,
                    phone = user.phone,
                    city = user.city,
                    password = user.password,
                    country = user.country,
                    title = user.organisation!!.title
                )
            )
        }
    }

    override fun getUserById(id: Int): Single<User> {
        return userApi.getUserById(id)
    }

    override fun logout(token: String): Observable<Boolean> {
        return userApi.logout(token)
    }

    override fun editUser(user: User): Observable<Boolean> {
        return if (user.human != null) {
            userApi.editHuman(
                HumanRequest(
                    id = user.id,
                    email = user.email,
                    phone = user.phone,
                    city = user.city,
                    country = user.country,
                    firstName = user.human.firstName!!,
                    lastName = user.human.lastName!!,
                    patronymic = user.human.patronymic,
                    photo = user.human.photo
                )
            )
        } else {
            Observable.just(true)
        }
    }
}