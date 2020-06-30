package com.example.shelter.presentation.fragment_menu.news.model

import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.model.News

interface INewsModel {
    fun loadNews(): List<News>
}