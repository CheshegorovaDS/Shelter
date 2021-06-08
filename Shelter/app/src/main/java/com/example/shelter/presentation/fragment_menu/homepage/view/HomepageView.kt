package com.example.shelter.presentation.fragment_menu.homepage.view

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.model.News
import com.example.shelter.presentation.onBoarding.registration.model.UserType
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface HomepageView: BaseView {
    val downloadUser: PublishSubject<Unit>
    val clickOpenCard: PublishSubject<News>
    val clickLogout: PublishSubject<Unit>

    fun showLogoutDialog()

    fun showProgressBar(isVisible: Boolean)
    fun showCards(isVisible: Boolean)
    fun showUserInfo(isVisible: Boolean)
    fun showListIsEmpty(isVisible: Boolean)
    fun updateCards(list: List<News>)
    fun updateInfo(name: String, typeUser: UserType, email: String, phone: String)
    fun exit()
}