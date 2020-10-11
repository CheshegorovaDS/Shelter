package com.example.shelter.presentation.log_in_app.di

import com.example.shelter.presentation.log_in_app.reducer.ILogInAppReducer
import com.example.shelter.presentation.log_in_app.view.LogInAppFragment
import dagger.Component

@Component(modules = [LogInAppBinds::class])
interface LogInAppComponent {
    fun inject(logInAppFragment: LogInAppFragment)
    fun getLogInAppReducer(): ILogInAppReducer
}