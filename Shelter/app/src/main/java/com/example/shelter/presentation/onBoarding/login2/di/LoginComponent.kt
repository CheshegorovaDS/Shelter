package com.example.shelter.presentation.onBoarding.login2.di

import com.example.shelter.app.di.AppComponent
import com.example.shelter.presentation.onBoarding.login2.reducer.ILoginReducer
import com.example.shelter.presentation.onBoarding.login2.view.LoginFragment
import com.example.shelter.presentation.LoginScope
import dagger.Component

@LoginScope
@Component(modules = [LoginBinds::class],
    dependencies = [AppComponent::class]
    )
interface LoginComponent {
    fun inject(loginFragment: LoginFragment)
    fun getReducer(): ILoginReducer
}