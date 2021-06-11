package com.example.shelter.data.news.repository

import com.example.shelter.presentation.fragment_menu.news.model.FilterNews
import com.example.shelter.presentation.model.News
import io.reactivex.Observable
import io.reactivex.Single

interface INewsRepository {
    fun getListNews(): Observable<List<News>>
    fun getListNewsByFilters(filters: FilterNews): Observable<List<News>>
    fun getListNewsByString(request: String): Observable<List<News>>
    fun getNewsById(id: Int): Single<News>
    fun getNewsByUser(idUser: Int): Observable<List<News>>
    fun addNews(news: News): Observable<Boolean>
    fun changeNews(news: News): Observable<Boolean>
    fun deleteNews(id: Int): Observable<Boolean>
}