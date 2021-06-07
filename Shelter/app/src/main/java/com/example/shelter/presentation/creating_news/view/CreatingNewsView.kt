package com.example.shelter.presentation.creating_news.view

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.model.AnimalType
import com.example.shelter.presentation.model.Category
import com.example.shelter.presentation.model.News
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface CreatingNewsView: BaseView {
    val tryCreateNews: PublishSubject<Animal>
    val downloadParameters: PublishSubject<Unit>

    fun clickCategory(): Observable<Any>
    fun clickAnimalType(): Observable<Any>
    fun clickGender(): Observable<Any>

    fun showAnimalForm(isVisible: Boolean)
    fun showAddCard(isVisible: Boolean)
    fun showException(isVisible: Boolean)
    fun showProgressBar(isVisible: Boolean)

    fun showCategoriesDialog(list: List<Category>)
    fun showAnimalTypesDialog(list: List<AnimalType>)
    fun showGenderDialog()

    fun closeWindow()
}