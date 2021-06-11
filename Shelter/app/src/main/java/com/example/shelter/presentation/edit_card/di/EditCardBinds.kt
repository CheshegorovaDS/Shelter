package com.example.shelter.presentation.edit_card.di

import com.example.shelter.presentation.edit_card.reducer.EditCardReducer
import com.example.shelter.presentation.edit_card.reducer.IEditCardReducer
import dagger.Binds
import dagger.Module

@Module
abstract class EditCardBinds {

    @Binds
    abstract fun bindEditCardReducer(reducer: EditCardReducer): IEditCardReducer

}