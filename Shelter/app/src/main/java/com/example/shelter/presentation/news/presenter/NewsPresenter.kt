package com.example.shelter.presentation.news.presenter

import com.example.shelter.presentation.base.inrefaces.BasePresenter
import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.news.model.NewsState
import com.example.shelter.presentation.news.reducer.INewsReducer
import com.example.shelter.presentation.news.view.NewsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class NewsPresenter @Inject constructor(
    var reducer: INewsReducer
): BasePresenter {
//    private var reducer: INewsReducer = NewsReducer()
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
            reducer.downloadNews()
        }?.addTo(disposeContainer)

        view?.clickAddCard()?.subscribe {
            reducer.addNews()
        }?.addTo(disposeContainer)

        view?.clickOpenCard?.subscribe {
            reducer.openCard(it)
        }?.addTo(disposeContainer)
//
//        view?.clickFilter()?.subscribe {
//
//        }?.addTo(disposeContainer)
//
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
    }
}