package com.example.shelter.presentation.filtering_news.di

import com.example.shelter.presentation.filtering_news.reducer.FilteringNewsReducer
import com.example.shelter.presentation.filtering_news.reducer.IFilteringNewsReducer
import dagger.Binds
import dagger.Module

@Module
abstract class FilteringNewsBinds {
    @Binds
    abstract fun bindFilteringNewsReducer(reducer: FilteringNewsReducer): IFilteringNewsReducer
}