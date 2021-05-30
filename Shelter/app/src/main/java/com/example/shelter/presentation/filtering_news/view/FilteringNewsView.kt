package com.example.shelter.presentation.filtering_news.view

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.model.AnimalType
import com.example.shelter.presentation.model.Category
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface FilteringNewsView: BaseView {
    val downloadData: PublishSubject<Unit>

    fun clickApply(): Observable<Any>
    fun clickChoosingCategory(): Observable<Any>
    fun clickChoosingAnimalTypes(): Observable<Any>

    fun updateCategories(list: List<Category>)
    fun updateAnimalTypes(list: List<AnimalType>)
    fun applyFilters()
    fun progressBarVisibility(isVisible: Boolean)
    fun categoriesVisibility(isVisible: Boolean)
    fun animalTypesVisibility(isVisible: Boolean)
    fun applyVisibility(isVisible: Boolean)
}