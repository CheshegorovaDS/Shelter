package com.example.shelter.presentation.fragment_menu.homepage.di

import com.example.shelter.app.di.AppComponent
import com.example.shelter.data.di.NewsRepositoryComponent
import com.example.shelter.data.di.UserRepositoryComponent
import com.example.shelter.presentation.fragment_menu.homepage.reducer.IHomepageReducer
import com.example.shelter.presentation.fragment_menu.homepage.view.HomepageFragment
import com.example.shelter.utils.HomepageScope
import dagger.Component

@HomepageScope
@Component(
    modules = [HomepageBinds::class],
    dependencies = [
        AppComponent::class,
        NewsRepositoryComponent::class,
        UserRepositoryComponent::class
    ]
)
interface HomepageComponent {
    fun inject(homepageFragment: HomepageFragment)
    fun getReducer(): IHomepageReducer
}