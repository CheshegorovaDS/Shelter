package com.example.shelter.presentation.creating_news.presenter

import com.example.shelter.presentation.base.inrefaces.BasePresenter
import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.creating_news.reducer.ICreatingNewsReducer
import com.example.shelter.presentation.creating_news.view.CreatingNewsActivity
import com.example.shelter.presentation.creating_news.view.CreatingNewsView
import io.reactivex.disposables.CompositeDisposable

class CreatingNewsPresenter constructor(
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
        TODO("Not yet implemented")
    }
}