package com.example.shelter.presentation.creating_news.di

import com.example.shelter.presentation.creating_news.reducer.CreatingNewsReducer
import com.example.shelter.presentation.creating_news.reducer.ICreatingNewsReducer
import dagger.Binds
import dagger.Module

@Module
abstract class CreatingNewsBinds {
    @Binds
    abstract fun bindCreatingNewsReducer(reducer: CreatingNewsReducer): ICreatingNewsReducer
}