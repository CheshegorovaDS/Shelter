package com.example.shelter.data.news.repository

import com.example.shelter.presentation.model.News
import io.reactivex.Observable
import io.reactivex.Single

interface INewsRepository {
    fun getListNews(): Observable<List<News>>
    fun getListNewsByCategory(category: String): Observable<List<News>>
    fun getNewsById(id: Int): Single<News>
    fun addNews(news: News)
    fun changeNews(id: Long, news: News)
}