package com.example.shelter.data.news.repository

import com.example.shelter.data.news.request.NewsRequest
import com.example.shelter.data.news.response.NewsResponse
import com.example.shelter.network.NetworkService
import com.example.shelter.network.NewsService
import com.example.shelter.network.Voter
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class NewsApi @Inject constructor(): INewsApi {

    val builder = NetworkService
    private val service = builder.buildUserService()
    val dispose = CompositeDisposable()

    override fun getNews2(): Single<Voter> {
        return service.getNewsList2().map {
            if (it.isSuccessful) {
                Voter(it.body()?.id ?: 0,
                    it.body()?.firstName ?:"",
                    it.body()?.lastName ?:"",
                    it.body()?.patronymic ?:"",
                    it.body()?.birthdate ?:"",
                    it.body()?.passport ?:"",
                    it.body()?.city ?:"",
                    it.body()?.street ?:"",
                    it.body()?.house ?:"",
                    it.body()?.flat ?:"")
            } else {
                throw Exception("fail")
            }
        }
    }

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