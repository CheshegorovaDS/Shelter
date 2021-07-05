package com.example.shelter.presentation.onBoarding.registration.reducer

import com.example.shelter.presentation.base.inrefaces.BaseReducer
import com.example.shelter.presentation.model.User
import com.example.shelter.presentation.onBoarding.registration.model.*
import io.reactivex.subjects.PublishSubject

interface IRegistrationReducer: BaseReducer {
    val updateDestination: PublishSubject<RegistrationDestination>
    val updateException: PublishSubject<RegistrationException>
    val updateState: PublishSubject<RegistrationState>

    fun updateUserType(type: UserType)
    fun tryToRegistration(user: User)
    fun registration()
}