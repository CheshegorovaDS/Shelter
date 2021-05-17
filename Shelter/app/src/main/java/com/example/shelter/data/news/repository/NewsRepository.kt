package com.example.shelter.data.news.repository

import com.example.shelter.presentation.model.News
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class NewsRepository @Inject constructor(
    var newsApi: INewsApi
): INewsRepository {

    override fun getListNews(): Observable<List<News>> {
        return newsApi.getNews()
    }

    override fun getListNewsByCategory(category: String): Observable<List<News>> {
        TODO()
//        return Observable.just(ad)
    }

    override fun getNewsById(id: Long): Observable<News> {
        TODO("Not yet implemented")
    }

    override fun addNews(news: News) {
        TODO("Not yet implemented")
    }

    override fun changeNews(id: Long, news: News) {
        TODO("Not yet implemented")
    }

}