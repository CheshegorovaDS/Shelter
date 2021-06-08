package com.example.shelter.presentation.edit_user.di

import com.example.shelter.presentation.edit_user.reducer.EditUserReducer
import com.example.shelter.presentation.edit_user.reducer.IEditUserReducer
import dagger.Binds
import dagger.Module

@Module
abstract class EditUserBinds {

    @Binds
    abstract fun bindEditUserReducer(reducer: EditUserReducer): IEditUserReducer

}