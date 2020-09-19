package com.example.shelter.presentation.onBoarding.registration.view

interface RegistrationView {
    fun showListTypeUser()
    fun navigateTo()
    fun showException()
    fun checkHuman(): Boolean
    fun checkOrganisation(): Boolean
}