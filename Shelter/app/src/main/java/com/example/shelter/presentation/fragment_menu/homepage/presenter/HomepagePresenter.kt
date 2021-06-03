package com.example.shelter.presentation.fragment_menu.homepage.presenter

import com.example.shelter.presentation.base.inrefaces.BasePresenter
import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.fragment_menu.homepage.reducer.IHomepageReducer
import com.example.shelter.presentation.fragment_menu.homepage.view.HomepageView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomepagePresenter @Inject constructor(
    private var reducer: IHomepageReducer
): BasePresenter {
    var view: HomepageView? = null

    private val disposeContainer = CompositeDisposable()

    override fun attachView(baseView: BaseView) {
        view = baseView as? HomepageView
        bind()
    }

    override fun detachView() {
        reducer.clearDispose()
        disposeContainer.clear()
    }

    override fun bind() {

    }
}