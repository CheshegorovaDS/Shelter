package com.example.shelter.presentation.creating_news.di

import com.example.shelter.data.di.AnimalTypeRepositoryComponent
import com.example.shelter.data.di.CategoryRepositoryComponent
import com.example.shelter.presentation.creating_news.reducer.ICreatingNewsReducer
import com.example.shelter.presentation.creating_news.view.CreatingNewsActivity
import com.example.shelter.utils.CreatingNewsScope
import dagger.Component

@CreatingNewsScope
@Component(
    modules = [CreatingNewsBinds::class],
    dependencies = [
        CategoryRepositoryComponent::class,
        AnimalTypeRepositoryComponent::class]
)
interface CreatingNewsComponent {
    fun inject(creatingNewsActivity: CreatingNewsActivity)
    fun getReducer(): ICreatingNewsReducer
}