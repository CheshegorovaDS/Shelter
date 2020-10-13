package com.example.shelter.presentation.onBoarding.login.reducer

import com.example.shelter.presentation.base.inrefaces.BaseReducer
import com.example.shelter.presentation.onBoarding.login.model.LoginDestination
import com.example.shelter.presentation.onBoarding.login.model.LoginException
import io.reactivex.subjects.PublishSubject

interface ILoginReducer: BaseReducer {
    val updateDestination: PublishSubject<LoginDestination>
    val updateError: PublishSubject<LoginException>
    fun login(login: String, password: String)
}