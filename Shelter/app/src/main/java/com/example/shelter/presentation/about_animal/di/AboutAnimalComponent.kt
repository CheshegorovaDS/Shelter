package com.example.shelter.presentation.about_animal.di

import com.example.shelter.app.di.AppComponent
import com.example.shelter.data.di.NewsRepositoryComponent
import com.example.shelter.data.di.UserRepositoryComponent
import com.example.shelter.presentation.about_animal.reducer.IAboutAnimalReducer
import com.example.shelter.presentation.about_animal.view.AboutAnimalActivity
import com.example.shelter.utils.AboutAnimalScope
import dagger.Component

@AboutAnimalScope
@Component(
    modules = [AboutAnimalBinds::class],
    dependencies = [
    AppComponent::class,
    NewsRepositoryComponent::class,
    UserRepositoryComponent::class
    ]
)
interface AboutAnimalComponent {
    fun inject(aboutAnimalActivity: AboutAnimalActivity)
    fun getReducer(): IAboutAnimalReducer
}