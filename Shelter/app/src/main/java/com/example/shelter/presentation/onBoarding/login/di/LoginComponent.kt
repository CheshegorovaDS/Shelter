package com.example.shelter.presentation.onBoarding.login.di

import com.example.shelter.app.di.AppComponent
import com.example.shelter.presentation.onBoarding.login.reducer.ILoginReducer
import com.example.shelter.presentation.onBoarding.login.view.LoginFragment
import com.example.shelter.utils.LoginScope
import dagger.Component

@LoginScope
@Component(modules = [LoginBinds::class],
    dependencies = [AppComponent::class]
    )
interface LoginComponent {
    fun inject(loginFragment: LoginFragment)
    fun getReducer(): ILoginReducer
}