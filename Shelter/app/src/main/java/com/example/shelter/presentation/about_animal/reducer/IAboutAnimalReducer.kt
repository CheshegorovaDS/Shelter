package com.example.shelter.presentation.about_animal.reducer

import com.example.shelter.presentation.about_animal.model.AboutAnimalException
import com.example.shelter.presentation.about_animal.model.AboutAnimalState
import com.example.shelter.presentation.base.inrefaces.BaseReducer
import com.example.shelter.presentation.model.News
import io.reactivex.subjects.PublishSubject

interface IAboutAnimalReducer: BaseReducer {
    val updateNews: PublishSubject<News>
    val updateState: PublishSubject<AboutAnimalState>
    val updateException: PublishSubject<AboutAnimalException>
    val updateDestination: PublishSubject<Int>

    fun downloadNews(idAnimal: Int)
    fun openUserPage()
}