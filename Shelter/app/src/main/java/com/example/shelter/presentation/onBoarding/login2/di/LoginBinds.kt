package com.example.shelter.presentation.onBoarding.login2.di

import com.example.shelter.presentation.onBoarding.login2.presenter.ILoginPresenter
import com.example.shelter.presentation.onBoarding.login2.presenter.LoginPresenter
import com.example.shelter.presentation.onBoarding.login2.reducer.ILoginReducer
import com.example.shelter.presentation.onBoarding.login2.reducer.LoginReducer
import dagger.Binds
import dagger.Module

@Module
abstract class LoginBinds {
    @Binds
    abstract fun bindLoginPresenter(presenter: LoginPresenter): ILoginPresenter

    @Binds
    abstract fun bindLoginReducer(reducer: LoginReducer): ILoginReducer
}