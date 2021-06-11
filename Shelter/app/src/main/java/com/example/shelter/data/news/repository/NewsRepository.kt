package com.example.shelter.data.news.repository

import com.example.shelter.data.news.request.NewsRequest
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

    override fun getListNewsByString(request: String): Observable<List<News>> {
        return newsApi.getNewsByString(request)
    }

    override fun getNewsById(id: Int): Single<News> {
        return newsApi.getNewsById(id)
    }

    override fun getNewsByUser(idUser: Int): Observable<List<News>> {
        return newsApi.getNewsByUserId(idUser)
    }

    override fun addNews(news: News): Observable<Boolean> {
        return newsApi.addNews(
            NewsRequest(
                idUser = news.idUser,
                idCategory = news.idCategory!!,
                nameAnimal = news.name,
                photoAnimal = news.photo,
                ageAnimal = news.age,
                breedAnimal = news.breed,
                idAnimalType = news.idAnimalType!!,
                sexAnimal = news.sex!!,
                passportAnimal = news.passport,
                descriptionAnimal = news.description
            )
        )
    }

    override fun changeNews(id: Int, news: News): Observable<Boolean> {
       TODO()
    }

    override fun deleteNews(id: Int): Observable<Boolean> {
        return newsApi.deleteNews(id)
    }
}
