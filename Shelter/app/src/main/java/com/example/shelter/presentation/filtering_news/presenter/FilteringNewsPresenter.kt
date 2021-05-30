package com.example.shelter.presentation.filtering_news.presenter

import com.example.shelter.presentation.base.inrefaces.BasePresenter
import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.filtering_news.model.FilteringNewsState
import com.example.shelter.presentation.filtering_news.reducer.IFilteringNewsReducer
import com.example.shelter.presentation.filtering_news.view.FilteringNewsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class FilteringNewsPresenter @Inject constructor(
    private var reducer: IFilteringNewsReducer
): BasePresenter {

    var view: FilteringNewsView? = null

    private val disposeContainer = CompositeDisposable()

    override fun attachView(baseView: BaseView) {
        view = baseView as? FilteringNewsView
        bind()
    }

    override fun detachView() {
        reducer.clearDispose()
        disposeContainer.clear()
    }

    override fun bind() {
        view?.downloadData?.subscribe {
            reducer.downloadInfo()
        }?.addTo(disposeContainer)

        view?.clickChoosingCategory()?.subscribe {
            reducer.showCategories()
        }?.addTo(disposeContainer)

        view?.clickChoosingAnimalTypes()?.subscribe {
            reducer.showAnimalTypes()
        }?.addTo(disposeContainer)

        view?.clickApply()?.subscribe {
            reducer.applyFilters()
        }?.addTo(disposeContainer)

        reducer.updateState
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                renderState(it)
            }.addTo(disposeContainer)

        reducer.updateCategories
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            view?.updateCategories(it)
        }.addTo(disposeContainer)

        reducer.updateAnimalTypes
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            view?.updateAnimalTypes(it)
        }.addTo(disposeContainer)

        reducer.applyFilters
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            view?.applyFilters()
        }.addTo(disposeContainer)

    }

    private fun renderState(state: FilteringNewsState) {
        view?.progressBarVisibility(state.progressBarVisibility)
        view?.categoriesVisibility(state.categoriesVisibility)
        view?.animalTypesVisibility(state.animalTypesVisibility)
        view?.applyVisibility(state.applyVisibility)
    }
}