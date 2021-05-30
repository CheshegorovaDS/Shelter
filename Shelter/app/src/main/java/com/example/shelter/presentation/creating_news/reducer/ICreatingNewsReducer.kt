package com.example.shelter.presentation.creating_news.reducer

import com.example.shelter.presentation.base.inrefaces.BaseReducer
import com.example.shelter.presentation.model.AnimalType
import com.example.shelter.presentation.model.Category
import io.reactivex.subjects.PublishSubject

interface ICreatingNewsReducer: BaseReducer {
    val updateCategory: PublishSubject<List<Category>>
    val updateAnimalType: PublishSubject<List<AnimalType>>

    fun download()
}