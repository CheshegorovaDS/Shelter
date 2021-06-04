package com.example.shelter.presentation.onBoarding.login.di

import com.example.shelter.app.di.AppComponent
import com.example.shelter.data.di.UserRepositoryComponent
import com.example.shelter.presentation.onBoarding.login.reducer.ILoginReducer
import com.example.shelter.presentation.onBoarding.login.view.LoginFragment
import com.example.shelter.utils.LoginScope
import dagger.Component

@LoginScope
@Component(modules = [LoginBinds::class],
    dependencies = [
        AppComponent::class,
        UserRepositoryComponent::class
    ]
)
interface LoginComponent {
    fun inject(loginFragment: LoginFragment)
    fun getReducer(): ILoginReducer
}