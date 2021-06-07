package com.example.shelter.presentation.creating_news.presenter

import com.example.shelter.presentation.base.inrefaces.BasePresenter
import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.creating_news.model.CreatingNewsState
import com.example.shelter.presentation.creating_news.reducer.ICreatingNewsReducer
import com.example.shelter.presentation.creating_news.view.CreatingNewsActivity
import com.example.shelter.presentation.creating_news.view.CreatingNewsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class CreatingNewsPresenter @Inject constructor(
    private var reducer: ICreatingNewsReducer
): BasePresenter {

    private var view: CreatingNewsView? = null
    private val dispose = CompositeDisposable()

    override fun attachView(baseView: BaseView) {
        view = baseView as CreatingNewsActivity
        bind()
    }

    override fun detachView() {
        reducer.clearDispose()
        dispose.clear()
    }

    override fun bind() {
        view?.downloadParameters?.subscribe {
            reducer.download()
        }?.addTo(dispose)

        view?.tryCreateNews?.subscribe {
            reducer.tryAddNews(it)
        }?.addTo(dispose)

        view?.clickCategory()?.subscribe {
            reducer.showCategories()
        }?.addTo(dispose)

        view?.clickAnimalType()?.subscribe {
            reducer.showAnimalTypes()
        }?.addTo(dispose)

        view?.clickGender()?.subscribe {
            reducer.showGender()
        }?.addTo(dispose)

        reducer.updateState
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                renderState(it)
            }.addTo(dispose)

        reducer.updateCategory
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.showCategoriesDialog(it)
            }.addTo(dispose)

        reducer.updateAnimalType
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.showAnimalTypesDialog(it)
            }.addTo(dispose)

        reducer.updateGender
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.showGenderDialog()
            }.addTo(dispose)

        reducer.updateDestination
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.closeWindow()
            }.addTo(dispose)
    }

    private fun renderState(state: CreatingNewsState) {
        view?.showAnimalForm(state.animalFormVisibility)
        view?.showAddCard(state.addCardVisibility)
        view?.showException(state.exceptionVisibility)
        view?.showProgressBar(state.progressBarVisibility)
    }
}