package com.example.shelter.presentation.onBoarding.registration.di

import com.example.shelter.app.di.AppComponent
import com.example.shelter.data.di.UserRepositoryComponent
import com.example.shelter.utils.RegistrationScope
import com.example.shelter.presentation.onBoarding.registration.reducer.IRegistrationReducer
import com.example.shelter.presentation.onBoarding.registration.view.RegistrationFragment
import dagger.Component

@RegistrationScope
@Component(modules = [RegistrationBinds::class],
    dependencies = [
        AppComponent::class,
        UserRepositoryComponent::class
    ]
)
interface RegistrationComponent {
    fun inject(registrationFragment: RegistrationFragment)
    fun getReducer(): IRegistrationReducer
}