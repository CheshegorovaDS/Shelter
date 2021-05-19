package com.example.shelter.presentation.about_animal.di

import com.example.shelter.presentation.about_animal.reducer.AboutAnimalReducer
import com.example.shelter.presentation.about_animal.reducer.IAboutAnimalReducer
import dagger.Binds
import dagger.Module

@Module
internal abstract class AboutAnimalBinds {
    @Binds
    abstract fun bindAboutAnimalReducer(reducer: AboutAnimalReducer): IAboutAnimalReducer
}