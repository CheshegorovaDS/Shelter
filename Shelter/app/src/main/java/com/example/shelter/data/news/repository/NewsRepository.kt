package com.example.shelter.data.news.repository

import com.example.shelter.presentation.fragment_menu.news.model.FilterNews
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

    override fun getListNewsByFilters(filters: FilterNews): Observable<List<News>> {
        return newsApi.getListNewsByFilters(filters)
    }

    override fun getNewsById(id: Int): Single<News> {
        return newsApi.getNewsById(id)
    }

    override fun addNews(news: News) {
        TODO("Not yet implemented")
    }

    override fun changeNews(id: Long, news: News) {
        TODO("Not yet implemented")
    }

}