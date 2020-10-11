package com.example.shelter.presentation.log_in_app.di

import com.example.shelter.presentation.log_in_app.presenter.ILogInAppPresenter
import com.example.shelter.presentation.log_in_app.presenter.LogInAppPresenter
import com.example.shelter.presentation.log_in_app.reducer.ILogInAppReducer
import com.example.shelter.presentation.log_in_app.reducer.LogInAppReducer
import dagger.Binds
import dagger.Module

@Module
abstract class LogInAppBinds {

    @Binds
    abstract fun bindLoginInAppPresenter(presenter: LogInAppPresenter): ILogInAppPresenter

    @Binds
    abstract fun bindLoginInAppReducer(reducer: LogInAppReducer): ILogInAppReducer
}
