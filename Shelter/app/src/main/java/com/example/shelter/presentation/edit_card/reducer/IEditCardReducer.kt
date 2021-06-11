package com.example.shelter.presentation.edit_card.reducer

import com.example.shelter.presentation.base.inrefaces.BaseReducer
import com.example.shelter.presentation.edit_card.model.EditCardDestination
import com.example.shelter.presentation.edit_card.model.EditCardException
import com.example.shelter.presentation.edit_card.model.EditCardState
import com.example.shelter.presentation.model.News
import io.reactivex.subjects.PublishSubject

interface IEditCardReducer: BaseReducer {
    val updateState: PublishSubject<EditCardState>
    val updateException: PublishSubject<EditCardException>
    val updateCard: PublishSubject<News>
    val updateDestination: PublishSubject<EditCardDestination>

    fun editCard(newCard: News)
    fun downloadInfo(id: Int)
}
