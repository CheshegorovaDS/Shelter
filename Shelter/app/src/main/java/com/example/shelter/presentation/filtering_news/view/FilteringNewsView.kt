package com.example.shelter.presentation.filtering_news.view

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.model.AnimalType
import com.example.shelter.presentation.model.Category
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface FilteringNewsView: BaseView {
    val downloadData: PublishSubject<Unit>
    val updateCheckedType: PublishSubject<List<AnimalType>>
    val updateCheckedCategory: PublishSubject<List<Category>>
    val clickApplyFilters: PublishSubject<Pair<List<Category>, List<AnimalType>>>

    fun clickChoosingCategory(): Observable<Any>
    fun clickChoosingAnimalTypes(): Observable<Any>

    fun showCategoriesDialog(list: List<Pair<Category, Boolean>>)
    fun showAnimalTypesDialog(list: List<AnimalType>)
    fun updateCheckedCategories(list: List<Category>)
    fun updateCheckedTypes(list: List<AnimalType>)
    fun applyFilters()
    fun progressBarVisibility(isVisible: Boolean)
    fun categoriesVisibility(isVisible: Boolean)
    fun animalTypesVisibility(isVisible: Boolean)
    fun applyVisibility(isVisible: Boolean)
    fun listCategoryVisibility(isVisible: Boolean)
    fun listAnimalTypeVisibility(isVisible: Boolean)
}