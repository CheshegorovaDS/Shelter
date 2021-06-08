package com.example.shelter.presentation.edit_user.di

import com.example.shelter.app.di.AppComponent
import com.example.shelter.data.di.UserRepositoryComponent
import com.example.shelter.presentation.edit_user.reducer.IEditUserReducer
import com.example.shelter.presentation.edit_user.view.EditUserActivity
import com.example.shelter.utils.EditUserScope
import dagger.Component

@EditUserScope
@Component(
    modules = [EditUserBinds::class],
    dependencies = [
        AppComponent::class,
        UserRepositoryComponent::class
    ]
)
interface EditUserComponent {
    fun inject(activity: EditUserActivity)
    fun getReducer(): IEditUserReducer
}