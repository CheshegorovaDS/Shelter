package com.example.shelter.presentation.onBoarding.registration2.view

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.onBoarding.registration2.model.RegistrationDestination
import com.example.shelter.presentation.onBoarding.registration2.model.RegistrationException
import com.example.shelter.presentation.onBoarding.registration2.model.User
import com.example.shelter.presentation.onBoarding.registration2.model.UserType
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface RegistrationView: BaseView {
    val clickRegistration: PublishSubject<User>
    val updateUserType: PublishSubject<UserType>

    fun clickRegistration()
    fun clickYouAre(): Observable<Any>
    fun showError(code: RegistrationException)
    fun navigateTo(registerDestination: RegistrationDestination)
    fun showListTypeUser()
    fun setUserType(type: UserType?)
    fun setFirstNameVisible(isVisible: Boolean)
    fun setLastNameVisible(isVisible: Boolean)
    fun setOrganisationNameVisible(isVisible: Boolean)
}