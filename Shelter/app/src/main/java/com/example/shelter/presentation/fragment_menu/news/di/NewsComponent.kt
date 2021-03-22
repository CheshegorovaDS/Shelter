package com.example.shelter.presentation.fragment_menu.news.di

import com.example.shelter.app.di.AppComponent
import com.example.shelter.presentation.NewsScope
import com.example.shelter.presentation.fragment_menu.news.reducer.INewsReducer
import com.example.shelter.presentation.fragment_menu.news.view.NewsFragment
import dagger.Component

@NewsScope
@Component(
    modules = [NewsBinds::class],
    dependencies = [AppComponent::class]
)
interface NewsComponent {
    fun inject(newsFragment: NewsFragment)
    fun getReducer(): INewsReducer
}