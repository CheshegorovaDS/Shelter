package com.example.shelter.presentation.filtering_news.di

import com.example.shelter.data.di.AnimalTypeRepositoryComponent
import com.example.shelter.data.di.CategoryRepositoryComponent
import com.example.shelter.presentation.filtering_news.reducer.IFilteringNewsReducer
import com.example.shelter.presentation.filtering_news.view.FilteringNewsActivity
import com.example.shelter.utils.FilteringNewsScope
import dagger.Component

@FilteringNewsScope
@Component(
    modules = [FilteringNewsBinds::class],
    dependencies = [
    CategoryRepositoryComponent::class,
    AnimalTypeRepositoryComponent::class
    ]
)
interface FilteringNewsComponent {
    fun inject(filteringNewsActivity: FilteringNewsActivity)
    fun getReducer(): IFilteringNewsReducer
}