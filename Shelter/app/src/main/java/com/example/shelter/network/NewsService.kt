package com.example.shelter.network

import com.example.shelter.data.news.response.NewsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {

    @GET("/api/cards/")
    fun getNewsList(): Observable<Response<List<NewsResponse>>>
}