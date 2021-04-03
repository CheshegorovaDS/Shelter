package com.example.shelter.data.news.repository

import com.example.shelter.data.news.request.NewsRequest
import com.example.shelter.data.news.response.NewsResponse
import com.example.shelter.network.NetworkService
import com.example.shelter.network.NewsService
import io.reactivex.Observable
import javax.inject.Inject

class NewsApi @Inject constructor(): INewsApi {
    val builder = NetworkService
    private val service = NewsService()

    override fun getNews(): Observable<List<NewsResponse>> {
        return service.getNewsList()
    }

    override fun getListNewsByCategory(request: NewsRequest): Observable<List<NewsResponse>> {
        TODO("Not yet implemented")
    }

    override fun getNewsById(request: NewsRequest): Observable<NewsResponse> {
        return service.getNewsById(request)
    }

    override fun addNews(request: NewsRequest) {
        return service.addNews(request)
    }

    override fun changeNews(request: NewsRequest) {
        return service.changeNews(request)
    }
}