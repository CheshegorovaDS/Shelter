package com.example.shelter.presentation.edit_card.view

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.edit_card.model.EditCardErrorCode
import com.example.shelter.presentation.model.News
import io.reactivex.subjects.PublishSubject

interface EditCardView: BaseView {
    val downloadInfo: PublishSubject<Unit>
    val editCard: PublishSubject<News>

    fun showProgressBar(visibility: Boolean)
    fun showFields(visibility: Boolean)
    fun showApply(visibility: Boolean)
    fun showException(visibility: Boolean)
    fun setCarInfo(news: News)
    fun setException(exception: EditCardErrorCode)
    fun exit()
}