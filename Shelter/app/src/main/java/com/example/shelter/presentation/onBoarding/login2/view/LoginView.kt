package com.example.shelter.presentation.onBoarding.login2.view

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.onBoarding.login2.model.LoginDestination
import com.example.shelter.presentation.onBoarding.login2.model.LoginErrorCode
import com.example.shelter.presentation.onBoarding.login2.model.LoginException
import io.reactivex.subjects.PublishSubject

interface LoginView: BaseView {
    val clickLogin: PublishSubject<Pair<String, String>>

    fun clickLogin()
    fun showError(code: LoginException)
    fun navigateTo(loginDestination: LoginDestination)
}