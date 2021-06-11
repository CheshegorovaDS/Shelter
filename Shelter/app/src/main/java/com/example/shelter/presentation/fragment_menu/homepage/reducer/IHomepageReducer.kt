package com.example.shelter.presentation.fragment_menu.homepage.reducer

import com.example.shelter.presentation.base.inrefaces.BaseReducer
import com.example.shelter.presentation.fragment_menu.homepage.model.HomepageDestination
import com.example.shelter.presentation.fragment_menu.homepage.model.HomepageException
import com.example.shelter.presentation.fragment_menu.homepage.model.HomepageState
import com.example.shelter.presentation.model.News
import com.example.shelter.presentation.model.User
import io.reactivex.subjects.PublishSubject

interface IHomepageReducer: BaseReducer {
    val updateUser: PublishSubject<User>
    val updateNews: PublishSubject<List<News>>
    val updateState: PublishSubject<HomepageState>
    val updateException: PublishSubject<HomepageException>
    val updateDestination: PublishSubject<HomepageDestination>

    fun editCard(news: News)
    fun deleteCard(id: Int)
    fun downloadUser()
    fun logout()
}