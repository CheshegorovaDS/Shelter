package com.example.shelter.data.news.repository

import com.example.shelter.network.Voter
import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.model.News
import io.reactivex.Observable
import io.reactivex.Single

interface INewsRepository {
    fun getVoter(): Single<Voter>
    fun getListNews(): Observable<List<Animal>>
    fun getListNewsByCategory(category: String): Observable<List<Animal>>
    fun getNewsById(id: Long): Observable<News>
    fun addNews(news: News)
    fun changeNews(id: Long, news: News)
}