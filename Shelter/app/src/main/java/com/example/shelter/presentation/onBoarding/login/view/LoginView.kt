package com.example.shelter.presentation.onBoarding.login.view

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.onBoarding.login.model.LoginDestination
import com.example.shelter.presentation.onBoarding.login.model.LoginException
import io.reactivex.subjects.PublishSubject

interface LoginView: BaseView {
    val clickLogin: PublishSubject<Pair<String, String>>

    fun clickLogin()
    fun showError(code: LoginException)
    fun navigateTo(loginDestination: LoginDestination)
}