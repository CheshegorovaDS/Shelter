package com.example.shelter.presentation.fragment_menu.news.presenter

import android.content.Context
import com.example.shelter.presentation.fragment_menu.news.INewsContact
import com.example.shelter.presentation.fragment_menu.news.model.INewsModel
import com.example.shelter.presentation.fragment_menu.news.model.NewsModel
import com.example.shelter.presentation.fragment_menu.news.view.NewsFragment
import com.example.shelter.presentation.model.News

class NewsPresenter: INewsContact.Presenter {
    var view: NewsFragment
    var model: INewsModel

    constructor(view: NewsFragment){
        this.view = view
        model = NewsModel()
    }

    override fun openNews(context: Context, photo: String) {
        TODO("Not yet implemented")
    }

    override fun loadNews() : List<News> {
        return model.loadNews()
    }

    override fun putLike() {
        TODO("Not yet implemented")
    }

    override fun addFilter() {
        TODO("Not yet implemented")
    }

    override fun searchAnimal() {
        TODO("Not yet implemented")
    }

    override fun openAnimalCard() {
        TODO("Not yet implemented")
    }

    override fun addCard() {
        TODO("Not yet implemented")
    }
}