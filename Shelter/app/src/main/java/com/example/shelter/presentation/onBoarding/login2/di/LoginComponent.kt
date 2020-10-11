package com.example.shelter.presentation.onBoarding.login2.di

import com.example.shelter.presentation.onBoarding.login2.reducer.ILoginReducer
import com.example.shelter.presentation.onBoarding.login2.view.LoginFragment
import dagger.Component

@Component(modules = [LoginBinds::class])
interface LoginComponent {
    fun inject(loginFragment: LoginFragment)
    fun getReducer(): ILoginReducer
}