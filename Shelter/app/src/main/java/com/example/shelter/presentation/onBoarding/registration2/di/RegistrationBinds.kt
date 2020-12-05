package com.example.shelter.presentation.onBoarding.registration2.di

import com.example.shelter.presentation.onBoarding.registration2.presenter.IRegistrationPresenter
import com.example.shelter.presentation.onBoarding.registration2.presenter.RegistrationPresenter
import com.example.shelter.presentation.onBoarding.registration2.reducer.IRegistrationReducer
import com.example.shelter.presentation.onBoarding.registration2.reducer.RegistrationReducer
import dagger.Binds
import dagger.Module

@Module
abstract class RegistrationBinds {

    @Binds
    abstract fun bindRegistrationPresenter(presenter: RegistrationPresenter): IRegistrationPresenter

    @Binds
    abstract fun bindRegistrationReducer(reducer: RegistrationReducer): IRegistrationReducer
}