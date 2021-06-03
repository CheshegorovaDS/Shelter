package com.example.shelter.presentation.fragment_menu.news.view

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.fragment_menu.news.model.FilterNews
import com.example.shelter.presentation.fragment_menu.news.model.NewsDestination
import com.example.shelter.presentation.model.News
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface NewsView: BaseView {
    val updateNews: PublishSubject<FilterNews>
    val clickOpenCard: PublishSubject<News>
    fun clickAddCard(): Observable<Any>
    fun clickSearch(): Observable<Any>
    fun clickFilter(): Observable<Any>

    fun showNews(list: List<News>)
    fun showProgressBar(visibility:Boolean)
    fun showListNews(visibility: Boolean)
    fun addNewsEnabled(isEnabled: Boolean)
    fun openCard(idCard: Int)
    fun navigateTo(destination: NewsDestination)
}