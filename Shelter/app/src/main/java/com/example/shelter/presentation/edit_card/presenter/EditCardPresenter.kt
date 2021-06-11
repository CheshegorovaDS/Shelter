package com.example.shelter.presentation.edit_card.presenter

import com.example.shelter.presentation.base.inrefaces.BasePresenter
import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.edit_card.model.EditCardState
import com.example.shelter.presentation.edit_card.reducer.IEditCardReducer
import com.example.shelter.presentation.edit_card.view.EditCardActivity
import com.example.shelter.presentation.edit_card.view.EditCardView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class EditCardPresenter @Inject constructor(
    private var reducer: IEditCardReducer
): BasePresenter {

    private var view: EditCardView? = null
    private val dispose = CompositeDisposable()

    override fun attachView(baseView: BaseView) {
        view = baseView as EditCardActivity
        bind()
    }

    override fun detachView() {
        reducer.clearDispose()
        dispose.clear()
    }

    override fun bind() {
        view?.downloadInfo?.subscribe {
            reducer.downloadInfo(0)
        }?.addTo(dispose)

        view?.editCard?.subscribe {
            reducer.editCard(it)
        }?.addTo(dispose)

        reducer.updateState
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                renderState(it)
            }.addTo(dispose)

        reducer.updateCard
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.setCarInfo(it)
            }.addTo(dispose)

        reducer.updateDestination
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.exit()
            }.addTo(dispose)
    }

    private fun renderState(state: EditCardState) {
        view?.showProgressBar(state.progressBarVisibility)
        view?.showException(state.exceptionVisibility)
        view?.showApply(state.applyVisibility)
    }
}