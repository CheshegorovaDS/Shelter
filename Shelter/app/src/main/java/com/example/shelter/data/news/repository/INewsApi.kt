package com.example.shelter.data.news.repository

import com.example.shelter.data.news.request.NewsRequest
import com.example.shelter.data.news.response.NewsResponse
import com.example.shelter.presentation.model.News
import io.reactivex.Observable

interface INewsApi {
    fun getNews(): Observable<List<News>>
    fun getListNewsByCategory(request: NewsRequest): Observable<List<NewsResponse>>
    fun getNewsById(request: NewsRequest): Observable<NewsResponse>
    fun addNews(request: NewsRequest)
    fun changeNews(request: NewsRequest)
}