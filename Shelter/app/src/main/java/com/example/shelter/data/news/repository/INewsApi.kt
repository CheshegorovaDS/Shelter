package com.example.shelter.data.news.repository

import com.example.shelter.data.news.request.NewsRequest
import com.example.shelter.data.news.response.NewsResponse
import com.example.shelter.network.Voter
import com.example.shelter.presentation.model.Animal
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

interface INewsApi {
    fun getNews(): Observable<List<NewsResponse>>
    fun getNews2(): Single<Voter>
    fun getListNewsByCategory(request: NewsRequest): Observable<List<NewsResponse>>
    fun getNewsById(request: NewsRequest): Observable<NewsResponse>
    fun addNews(request: NewsRequest)
    fun changeNews(request: NewsRequest)
}