package com.example.shelter.presentation.fragment_menu.homepage.view

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.model.News
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface HomepageView: BaseView {
    val downloadUser: PublishSubject<Unit>

    fun clickLogout(): Observable<Any>

    fun showProgressBar(isVisible: Boolean)
    fun showCards(list: List<News>)
//    fun showInfo()
}