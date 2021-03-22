package com.example.shelter.presentation.fragment_menu.news.di

import com.example.shelter.presentation.fragment_menu.news.reducer.INewsReducer
import com.example.shelter.presentation.fragment_menu.news.reducer.NewsReducer
import dagger.Binds
import dagger.Module

@Module
abstract class NewsBinds {
    @Binds
    abstract fun bindNewsReducer(reducer: NewsReducer): INewsReducer
}
