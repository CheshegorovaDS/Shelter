package com.example.shelter.presentation.onBoarding.login2.view

import com.example.shelter.presentation.base2.BaseView
import com.example.shelter.presentation.onBoarding.login2.model.LoginDestination
import com.example.shelter.presentation.onBoarding.login2.model.LoginErrorCode
import io.reactivex.Observable

interface LoginView: BaseView{
    fun clickLogin(): Observable<Pair<String, String>>
    fun showError(code: LoginErrorCode)
    fun navigateTo(loginDestination: LoginDestination)
}