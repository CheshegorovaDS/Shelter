package com.example.shelter.presentation.fragment_menu.homepage.di

import com.example.shelter.presentation.fragment_menu.homepage.reducer.HomepageReducer
import com.example.shelter.presentation.fragment_menu.homepage.reducer.IHomepageReducer
import dagger.Binds
import dagger.Module

@Module
abstract class HomepageBinds {
    @Binds
    abstract fun bindHomepageReducer(reducer: HomepageReducer): IHomepageReducer
}