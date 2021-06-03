package com.example.shelter.data.news.repository

import com.example.shelter.presentation.fragment_menu.news.model.FilterNews
import com.example.shelter.presentation.model.News
import io.reactivex.Observable
import io.reactivex.Single

interface INewsRepository {
    fun getListNews(): Observable<List<News>>
    fun getListNewsByFilters(filters: FilterNews): Observable<List<News>>
    fun getNewsById(id: Int): Single<News>
    fun addNews(news: News)
    fun changeNews(id: Long, news: News)
}