package com.example.shelter.presentation.filtering_news.reducer

import com.example.shelter.presentation.base.inrefaces.BaseReducer
import com.example.shelter.presentation.filtering_news.model.FilteringNewsException
import com.example.shelter.presentation.filtering_news.model.FilteringNewsState
import com.example.shelter.presentation.model.AnimalType
import com.example.shelter.presentation.model.Category
import io.reactivex.subjects.PublishSubject

interface IFilteringNewsReducer: BaseReducer {
    val updateState: PublishSubject<FilteringNewsState>
    val updateException: PublishSubject<FilteringNewsException>
    val updateCategories: PublishSubject<List<Pair<Category, Boolean>>>
    val updateAnimalTypes: PublishSubject<List<AnimalType>>
    val updateCheckedCategories: PublishSubject<List<Category>>
    val updateCheckedTypes: PublishSubject<List<AnimalType>>
    val applyFilters: PublishSubject<Unit>

    fun downloadInfo()
    fun showCategories()
    fun showAnimalTypes()
    fun applyFilters(checkedCategories: List<Category>, checkedTypes: List<AnimalType>)
    fun changeCheckedCategories(categories: List<Category>)
    fun changeCheckedTypes(types: List<AnimalType>)
}