package com.example.shelter.data.news.repository

import com.example.shelter.data.news.request.NewsRequest
import com.example.shelter.data.news.response.NewsResponse
import com.example.shelter.network.NetworkService
import com.example.shelter.presentation.model.News
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.lang.Exception
import javax.inject.Inject

class NewsApi @Inject constructor(): INewsApi {

    val builder = NetworkService
    private val service = builder.buildUserService()
    private val dispose = CompositeDisposable()

    override fun getNews(): Observable<List<News>> {
        return service.getNewsList().map {
            if (it.isSuccessful) {
                val list = mutableListOf<News>()
                it.body()?.forEach { news ->
                    list.add(News(news.id, news.name, news.photo, news.category))
                }
                list
            } else {
                throw Exception("fail")
            }
        }
    }


    override fun getListNewsByCategory(request: NewsRequest): Observable<List<NewsResponse>> {
        TODO("Not yet implemented")
    }

    override fun getNewsById(request: NewsRequest): Observable<NewsResponse> {
        TODO()
//        return service.getNewsById(request)
    }

    override fun addNews(request: NewsRequest) {
//        return service.addNews(request)
    }

    override fun changeNews(request: NewsRequest) {
//        return service.changeNews(request)
    }
}