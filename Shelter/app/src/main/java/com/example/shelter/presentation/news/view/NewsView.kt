package com.example.shelter.presentation.news.view

import android.content.Intent
import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.news.model.NewsDestination
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface NewsView {
    val updateNews: PublishSubject<Unit>
    val clickOpenCard: PublishSubject<Animal>
    fun clickAddCard(): Observable<Any>
    fun clickSearch(): Observable<Any>
    fun clickFilter(): Observable<Any>

    fun showNews(list: List<Animal>)
    fun openFilter()
    fun showProgressBar(visible:Boolean)
    fun addNewsEnabled(isEnabled: Boolean)
    fun openCard(intentAnimal: Intent)
    fun navigateTo(destination: NewsDestination)
}