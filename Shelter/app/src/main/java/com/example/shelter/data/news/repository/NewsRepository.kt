package com.example.shelter.data.news.repository

import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.model.News
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepository @Inject constructor(
    var newsApi: INewsApi
): INewsRepository {

    override fun getListNews(): Observable<List<Animal>> {
        return Observable.just(addList())
    }

    override fun getListNewsByCategory(category: String): Observable<List<Animal>> {
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

    private fun addList(): List<Animal> {
        val tables = ArrayList<Animal>()
        tables.add(Animal("Вася", ""))
        tables.add(Animal("Буся", "Ж",  age = 2))
        tables.add(Animal("Meca","Ж",age = 1))
        tables.add(Animal("Муся","Ж",age = 1))
        tables.add(Animal("Симбад","М",age = 1))
        tables.add(Animal("Миша","М",age = 2))
        return tables
    }

}