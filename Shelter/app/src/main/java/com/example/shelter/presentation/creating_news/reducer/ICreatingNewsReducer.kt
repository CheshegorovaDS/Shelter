package com.example.shelter.presentation.creating_news.reducer

import com.example.shelter.presentation.base.inrefaces.BaseReducer
import com.example.shelter.presentation.creating_news.model.CreatingNewsInfo
import com.example.shelter.presentation.creating_news.model.CreatingNewsState
import com.example.shelter.presentation.filtering_news.model.FilteringNewsException
import com.example.shelter.presentation.model.AnimalType
import com.example.shelter.presentation.model.Category
import io.reactivex.subjects.PublishSubject

interface ICreatingNewsReducer: BaseReducer {
    val updateState: PublishSubject<CreatingNewsState>
    val updateCategory: PublishSubject<List<Category>>
    val updateAnimalType: PublishSubject<List<AnimalType>>
    val updateException: PublishSubject<FilteringNewsException>
    val updateGender: PublishSubject<Unit>

    fun download()
    fun showCategories()
    fun showAnimalTypes()
    fun showGender()

}
