package com.example.shelter.data.news.repository

import com.example.shelter.data.news.request.NewsRequest
import com.example.shelter.presentation.fragment_menu.news.model.FilterNews
import com.example.shelter.presentation.model.News
import io.reactivex.Observable
import io.reactivex.Single

interface INewsApi {
    fun getNews(): Observable<List<News>>
    fun getListNewsByFilters(filtersRequest: FilterNews): Observable<List<News>>
    fun getNewsById(id: Int): Single<News>
    fun addNews(request: NewsRequest)
    fun changeNews(request: NewsRequest)
}