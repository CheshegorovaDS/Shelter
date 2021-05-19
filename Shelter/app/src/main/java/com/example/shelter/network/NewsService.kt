package com.example.shelter.network

import com.example.shelter.data.news.response.NewsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {

    @GET("/api/cards/")
    fun getNewsList(): Observable<Response<List<NewsResponse>>>

    @GET("/api/card/{id}")
    fun getNewsById(@Path("id")id: Int): Single<Response<NewsResponse>>
}