package com.example.shelter.data.news.repository

import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.model.News
import io.reactivex.Observable

interface INewsRepository {
    fun getListNews(): Observable<List<Animal>>
    fun getListNewsByCategory(category: String): Observable<List<Animal>>
    fun getNewsById(id: Long): Observable<News>
    fun addNews(news: News)
    fun changeNews(id: Long, news: News)
}