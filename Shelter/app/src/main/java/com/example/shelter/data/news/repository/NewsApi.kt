package com.example.shelter.data.news.repository

import com.example.shelter.data.news.request.NewsRequest
import com.example.shelter.data.news.response.NewsResponse
import com.example.shelter.network.NetworkService
import com.example.shelter.presentation.model.News
import com.example.shelter.presentation.model.User
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.lang.Exception
import javax.inject.Inject

class NewsApi @Inject constructor(): INewsApi {

    val builder = NetworkService
    private val service = builder.buildNewsService()
    private val dispose = CompositeDisposable()

    override fun getNews(): Observable<List<News>> {
        return service.getNewsList().map {
            if (it.isSuccessful) {
                val list = mutableListOf<News>()
                it.body()?.forEach { news ->
                    list.add(News(
                        news.id,
                        news.name,
                        news.photo,
                        news.category,
                        news.idUser
                    ))
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

    override fun getNewsById(id: Int): Single<News> {
        return service.getNewsById(id).map {
           if (it.isSuccessful) {
               it.body()?.let { n ->
                   News(
                       id = n.id,
                       name = n.name,
                       photo = n.photo,
                       category = n.category,
                       idUser = n.idUser,
                       age = n.age,
                       sex = n.sex,
                       breed = n.breed,
                       passport = n.passport,
                       description = n.description,
                       user = User(
                           id = n.idUser,
                           email = n.email,
                           phone = n.phone
                       )
                   )
               }
           } else {
               throw Exception("fail")
           }
        }
    }

    override fun addNews(request: NewsRequest) {
//        return service.addNews(request)
    }

    override fun changeNews(request: NewsRequest) {
//        return service.changeNews(request)
    }
}