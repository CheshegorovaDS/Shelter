package com.example.shelter.presentation.fragment_menu.news.reducer

import com.example.shelter.presentation.base.inrefaces.BaseReducer
import com.example.shelter.presentation.fragment_menu.news.model.FilterNews
import com.example.shelter.presentation.fragment_menu.news.model.NewsDestination
import com.example.shelter.presentation.fragment_menu.news.model.NewsException
import com.example.shelter.presentation.fragment_menu.news.model.NewsState
import com.example.shelter.presentation.model.News
import io.reactivex.subjects.PublishSubject

interface INewsReducer: BaseReducer {
    val updateNews: PublishSubject<List<News>>
    val updateState: PublishSubject<NewsState>
    val updateException: PublishSubject<NewsException>
    val openAnimalNews: PublishSubject<Int>
    val updateDestination: PublishSubject<NewsDestination>

    fun downloadNews()
    fun downloadNews(request: String)
    fun downloadNews(filters: FilterNews)
    fun openFilter()
    fun updateSearch(text: String)
    fun openCard(news: News)
    fun addNews()
    fun clearSearch()
}