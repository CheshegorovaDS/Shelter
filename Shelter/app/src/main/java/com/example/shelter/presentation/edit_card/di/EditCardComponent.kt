package com.example.shelter.presentation.edit_card.di

import com.example.shelter.app.di.AppComponent
import com.example.shelter.data.di.NewsRepositoryComponent
import com.example.shelter.presentation.edit_card.reducer.IEditCardReducer
import com.example.shelter.presentation.edit_card.view.EditCardActivity
import com.example.shelter.utils.EditCardScope
import dagger.Component

@EditCardScope
@Component(
    modules = [EditCardBinds::class],
    dependencies = [
        AppComponent::class,
        NewsRepositoryComponent::class
    ]
)
interface EditCardComponent {
    fun inject(activity: EditCardActivity)
    fun getReducer(): IEditCardReducer
}