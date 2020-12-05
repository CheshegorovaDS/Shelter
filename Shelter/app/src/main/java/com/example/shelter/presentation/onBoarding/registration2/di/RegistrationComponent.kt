package com.example.shelter.presentation.onBoarding.registration2.di

import com.example.shelter.app.di.AppComponent
import com.example.shelter.presentation.RegistrationScope
import com.example.shelter.presentation.onBoarding.registration2.reducer.IRegistrationReducer
import com.example.shelter.presentation.onBoarding.registration2.view.RegistrationFragment
import dagger.Component

@RegistrationScope
@Component(modules = [RegistrationBinds::class],
    dependencies = [AppComponent::class]
    )
interface RegistrationComponent {
    fun inject(registrationFragment: RegistrationFragment)
    fun getReducer(): IRegistrationReducer
}