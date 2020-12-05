package com.example.shelter.presentation.onBoarding.registration.di

import com.example.shelter.presentation.onBoarding.registration.presenter.IRegistrationPresenter
import com.example.shelter.presentation.onBoarding.registration.presenter.RegistrationPresenter
import com.example.shelter.presentation.onBoarding.registration.reducer.IRegistrationReducer
import com.example.shelter.presentation.onBoarding.registration.reducer.RegistrationReducer
import dagger.Binds
import dagger.Module

@Module
abstract class RegistrationBinds {

    @Binds
    abstract fun bindRegistrationPresenter(presenter: RegistrationPresenter): IRegistrationPresenter

    @Binds
    abstract fun bindRegistrationReducer(reducer: RegistrationReducer): IRegistrationReducer
}