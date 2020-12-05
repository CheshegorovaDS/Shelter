package com.example.shelter.presentation.storage

import com.example.shelter.presentation.onBoarding.registration.model.User


interface LoggedUserProvider {
    fun setLoggedUser(loggedUser: User)
    fun getLoggedUser(): User?
    fun logout()
}