package com.example.shelter.presentation.menu.di

import com.example.shelter.app.di.AppComponent
import com.example.shelter.presentation.MenuScope
import com.example.shelter.presentation.menu.MenuActivity
import dagger.Component

@MenuScope
@Component(dependencies = [AppComponent::class])
interface MenuComponent {
    fun inject(menuActivity: MenuActivity)
//    fun getActivity(): MenuActivity
}
