package com.example.shelter.presentation.edit_card.reducer

import com.example.shelter.data.news.repository.INewsRepository
import com.example.shelter.presentation.edit_card.model.EditCardDestination
import com.example.shelter.presentation.edit_card.model.EditCardException
import com.example.shelter.presentation.edit_card.model.EditCardState
import com.example.shelter.presentation.model.News
import com.example.shelter.presentation.model.User
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class EditCardReducer @Inject constructor(
    private var newsRepository: INewsRepository
): IEditCardReducer {

    override val updateState: PublishSubject<EditCardState> = PublishSubject.create()
    override val updateException: PublishSubject<EditCardException> = PublishSubject.create()
    override val updateCard: PublishSubject<News> = PublishSubject.create()
    override val updateDestination: PublishSubject<EditCardDestination> = PublishSubject.create()

    private val dispose = CompositeDisposable()
    private var state = EditCardState()
    private var news: News? = null

    override fun downloadInfo(id:Int) {
        state = EditCardState()
        updateState.onNext(state)

        dispose.add(
            newsRepository.getNewsById(id)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    news = it
                    updateCard.onNext(it)

                    state = state.copy(
                        cardFieldsVisibility = true,
                        progressBarVisibility = false,
                        applyVisibility = true
                    )
                    updateState.onNext(state)
                }, {
                    updateException.onNext(EditCardException())
                    state = EditCardState(
                        exceptionVisibility = true,
                        progressBarVisibility = false
                    )
                    updateState.onNext(state)
                })
        )
    }

    override fun editCard(newCard: News) {
        state = state.copy(
            progressBarVisibility = true
        )
        updateState.onNext(state)

        if (cardIsNotChanged(newCard)) {
            updateDestination.onNext(EditCardDestination.NEWS)
            return
        }

        //checkFields
        //change
        news = news?.copy(
            name = newCard.name,
            age = newCard.age,
            sex = newCard.sex
        )


        dispose.add(
            newsRepository.changeNews(news!!)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    state = state.copy(progressBarVisibility = false)
                    updateState.onNext(state)
                    updateDestination.onNext(EditCardDestination.NEWS)
                }, {
                    updateException.onNext(EditCardException())
                    state = state.copy(
                        progressBarVisibility = false,
                        exceptionVisibility = true
                    )
                    updateState.onNext(state)
                })
        )

    }

    private fun cardIsNotChanged(newCard: News): Boolean {
        return newCard.name == news?.name &&
                newCard.description == news?.description&&
                newCard.passport == news?.passport &&
                newCard.breed == news?.breed &&
                newCard.age == news?.age &&
                newCard.category == news?.category &&
                newCard.sex == news?.sex
    }


    override fun clearDispose() = dispose.clear()
}