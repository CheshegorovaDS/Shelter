package com.example.shelter.presentation.onBoarding.registration.presenter

import com.example.shelter.presentation.model.User

interface IRegistrationPresenter {
    fun register(user: User)
    fun showListUser()
}