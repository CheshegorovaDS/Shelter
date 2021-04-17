package com.example.shelter.presentation.creating_news.view

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.model.News
import io.reactivex.subjects.PublishSubject

interface CreatingNewsView: BaseView {
    val tryCreateNews: PublishSubject<Animal>

    fun showException(isVisible: Boolean)
    fun showProgressBar(isVisible: Boolean)
}