package com.example.shelter.network

import com.example.shelter.data.news.response.NewsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {

    @GET("/api/cards/")
    fun getNewsList(): Observable<Response<List<NewsResponse>>>

    @GET("/api/card/{id}")
    fun getNewsById(@Path("id")id: Int): Single<Response<NewsResponse>>

    @GET("/api/cards/filters")
    fun getNewsByFilters(
        @Query("category")categories: List<Int>,
        @Query("animal_type")types: List<Int>
    ): Observable<Response<List<NewsResponse>>>

    @GET("/api/cards/user={id}")
    fun getNewsByUserId(@Path("id")id: Int): Observable<Response<List<NewsResponse>>>
}