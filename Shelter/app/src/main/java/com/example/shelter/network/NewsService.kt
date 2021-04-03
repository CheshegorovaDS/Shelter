package com.example.shelter.network

import com.example.shelter.data.news.request.NewsRequest
import com.example.shelter.data.news.response.NewsResponse
import com.example.shelter.presentation.about_animal.model.Sex
import com.example.shelter.presentation.about_animal.model.Sterilization
import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.model.User
import io.reactivex.Observable


class NewsService {

    fun getNewsList(): Observable<List<NewsResponse>> {
        return Observable.just(getNews())
    }

    fun getNewsById(request: NewsRequest): Observable<NewsResponse> {
        return Observable.just(
            getNews().minBy {
                it.id == request.id
            }!!
        )
    }

    fun addNews(request: NewsRequest) {
        list.add(
            NewsResponse(
                request.id,
                Animal(
                    request.name,
                    photo = ""
                ),
                User("","", "", "","")
            )
        )
    }

    fun changeNews(request: NewsRequest) {
        for ((index, news) in list.withIndex()){
            if (news.id == request.id) {
                list[index] = NewsResponse(
                    request.id,
                    Animal(
                        request.name,
                        photo = ""
                    ),
                    User("","", "", "","")
                )
                return
            }
        }
    }

    companion object {
        val list = ArrayList<NewsResponse>()

        fun getNews(): List<NewsResponse>{
            list.add(
                NewsResponse(
                1L,
                    Animal(  "Bacя",
                       "https://avatars.mds.yandex.net/get-pdb/1058492/87a96367-4ac0-42c2-9ca3-c4de8e9077ff/s1200?webp=false",
                       "шотландская вислоухая",
                       "Кот",
                       Sex.M,
                       "шотландская вислоухая",
                       1,
                       "",
                       Sterilization.NO,
                       "",
                       "Любит спать, ласковый, постоянно мурчит, любит гонять собак. Есть только перепелинные яйца."
                    ),
                    User("","", "", "","")
                ))
            list.add(
                NewsResponse(
                    2L,
                    Animal(  "Муся",
                        "",
                        "",
                        "Кошка",
                        Sex.F,
                        "шотландская вислоухая",
                        3,
                        "",
                        Sterilization.NO,
                        "",
                        ""
                    ),
                    User("","", "", "","")
                ))
            return list
        }
    }
}