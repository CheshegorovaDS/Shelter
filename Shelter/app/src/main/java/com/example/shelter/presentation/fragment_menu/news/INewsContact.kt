package com.example.shelter.presentation.fragment_menu.news

import android.content.Context
import com.example.shelter.presentation.model.News

interface INewsContact {
    interface View{
        fun setFirstVisibleItem(item: Int)
        fun errorConnection(str: String)
        fun putLike()
    }

    interface Presenter{
        fun openNews(context: Context, photo: String)
        fun loadNews(): List<News>
        fun putLike()
        fun addFilter()
        fun searchAnimal()
        fun openAnimalCard()
        fun addCard()
    }
}