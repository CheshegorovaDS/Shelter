package com.example.shelter.presentation.onBoarding.login.di

import com.example.shelter.presentation.onBoarding.login.presenter.ILoginPresenter
import com.example.shelter.presentation.onBoarding.login.presenter.LoginPresenter
import com.example.shelter.presentation.onBoarding.login.reducer.ILoginReducer
import com.example.shelter.presentation.onBoarding.login.reducer.LoginReducer
import dagger.Binds
import dagger.Module

@Module
abstract class LoginBinds {
    @Binds
    abstract fun bindLoginPresenter(presenter: LoginPresenter): ILoginPresenter

    @Binds
    abstract fun bindLoginReducer(reducer: LoginReducer): ILoginReducer
}
