package com.example.shelter.presentation.registration.presenter

import com.example.shelter.presentation.model.User

interface IRegistrationPresenter {
    fun register(user: User)
    fun showListUser()
}