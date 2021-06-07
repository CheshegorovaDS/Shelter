package com.example.shelter.presentation.creating_news.reducer

import com.example.shelter.presentation.base.inrefaces.BaseReducer
import com.example.shelter.presentation.creating_news.model.CreatingNewsException
import com.example.shelter.presentation.creating_news.model.CreatingNewsState
import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.model.AnimalType
import com.example.shelter.presentation.model.Category
import io.reactivex.subjects.PublishSubject

interface ICreatingNewsReducer: BaseReducer {
    val updateState: PublishSubject<CreatingNewsState>
    val updateDestination: PublishSubject<Unit>
    val updateCategory: PublishSubject<List<Category>>
    val updateAnimalType: PublishSubject<List<AnimalType>>
    val updateException: PublishSubject<CreatingNewsException>
    val updateGender: PublishSubject<Unit>

    fun download()
    fun tryAddNews(animal: Animal)
    fun showCategories()
    fun showAnimalTypes()
    fun showGender()

}
