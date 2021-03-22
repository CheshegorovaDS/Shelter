package com.example.shelter.presentation.fragment_menu.news.reducer

import android.content.Intent
import com.example.shelter.presentation.base.inrefaces.BaseReducer
import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.fragment_menu.news.model.NewsDestination
import com.example.shelter.presentation.fragment_menu.news.model.NewsException
import com.example.shelter.presentation.fragment_menu.news.model.NewsState
import io.reactivex.subjects.PublishSubject

interface INewsReducer: BaseReducer {
    val updateNews: PublishSubject<List<Animal>>
    val updateState: PublishSubject<NewsState>
    val updateException: PublishSubject<NewsException>
    val openAnimalNews: PublishSubject<Intent>
    val updateDestination: PublishSubject<NewsDestination>

    fun downloadNews()
    fun downloadNews(category: String)
    fun openFilter()
    fun openCard(animal: Animal)
    fun addNews()
}