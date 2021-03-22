package com.example.shelter.presentation.news.di

import com.example.shelter.presentation.news.reducer.INewsReducer
import com.example.shelter.presentation.news.reducer.NewsReducer
import dagger.Binds
import dagger.Module

@Module
abstract class NewsBinds {
    @Binds
    abstract fun bindNewsReducer(reducer: NewsReducer): INewsReducer
}
