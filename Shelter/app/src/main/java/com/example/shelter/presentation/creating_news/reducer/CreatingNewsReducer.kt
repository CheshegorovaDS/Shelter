package com.example.shelter.presentation.creating_news.reducer

import com.example.shelter.presentation.model.AnimalType
import com.example.shelter.presentation.model.Category
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class CreatingNewsReducer : ICreatingNewsReducer {

    private val dispose = CompositeDisposable()
    override val updateCategory: PublishSubject<List<Category>> = PublishSubject.create()
    override val updateAnimalType: PublishSubject<List<AnimalType>> = PublishSubject.create()

    override fun download() {
        TODO("Not yet implemented")
    }

    override fun clearDispose() = dispose.clear()

}