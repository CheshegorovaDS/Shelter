package com.example.shelter.presentation.fragment_menu.news.presenter

import com.example.shelter.presentation.base.inrefaces.BasePresenter
import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.fragment_menu.news.model.FilterNews
import com.example.shelter.presentation.fragment_menu.news.model.NewsState
import com.example.shelter.presentation.fragment_menu.news.reducer.INewsReducer
import com.example.shelter.presentation.fragment_menu.news.view.NewsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class NewsPresenter @Inject constructor(
    private var reducer: INewsReducer
): BasePresenter {

    var view: NewsView? = null

    private val disposeContainer = CompositeDisposable()

    override fun attachView(baseView: BaseView) {
        view = baseView as? NewsView
        bind()
    }

    override fun detachView() {
        reducer.clearDispose()
        disposeContainer.clear()
    }

    override fun bind() {
        view?.updateNews?.subscribe {
            val l1 = it.listCategoriesId.filter {  id -> id > 0 }
            val l2 = it.listTypesId.filter { id -> id > 0 }

            if (l1.isEmpty() && l2.isEmpty()) {
                reducer.downloadNews()
            } else {
                reducer.downloadNews(FilterNews(l1, l2))
            }
        }?.addTo(disposeContainer)

        view?.clickAddCard()?.subscribe {
            reducer.addNews()
        }?.addTo(disposeContainer)

        view?.clickOpenCard?.subscribe {
            reducer.openCard(it)
        }?.addTo(disposeContainer)

        view?.clickFilter()?.subscribe {
            reducer.openFilter()
        }?.addTo(disposeContainer)

//        view?.clickSearch()?.subscribe {
//
//        }?.addTo(disposeContainer)

        reducer.updateState
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                renderState(it)
            }.addTo(disposeContainer)

        reducer.updateNews
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.showNews(it)
            }.addTo(disposeContainer)

        reducer.updateDestination
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.navigateTo(it)
            }.addTo(disposeContainer)

        reducer.openAnimalNews
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.openCard(it)
            }.addTo(disposeContainer)
    }

    private fun renderState(state: NewsState){
        view?.showProgressBar(state.progressBarVisibility)
        view?.showListNews(state.listVisibility)
    }
}