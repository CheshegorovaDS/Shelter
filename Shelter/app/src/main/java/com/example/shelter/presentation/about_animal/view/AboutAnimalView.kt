package com.example.shelter.presentation.about_animal.view

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.model.News
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface AboutAnimalView: BaseView {
    val downloadNews: PublishSubject<Int>
    fun updateNews(news: News)
    fun clickUserCard(): Observable<Any>
    fun showAnimalInfo(name: String)
    fun showPhotoUser(visibility: Boolean)
    fun showBreed(visibility: Boolean)
    fun showAge(visibility: Boolean)
    fun showPassport(visibility: Boolean)
    fun showSex(visibility: Boolean)
    fun showUser(visibility: Boolean)
    fun showDescription(visibility: Boolean)
    fun showPhoto(visibility: Boolean)
    fun showName(visibility: Boolean)
    fun showCategory(visibility: Boolean)
    fun showProgressBar(visibility: Boolean)
    fun openUserHomepage(idUser: Int)
}