package com.example.shelter.presentation.onBoarding.login2.presenter

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.onBoarding.login2.reducer.ILoginReducer
import com.example.shelter.presentation.onBoarding.login2.reducer.LoginReducer
import com.example.shelter.presentation.onBoarding.login2.view.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    var reducer: ILoginReducer
): ILoginPresenter {

    var view: LoginView? = null

    private val disposeContainer = CompositeDisposable()

    override fun attachView(baseView: BaseView) {
        view = baseView as? LoginView
        bind()
    }

    override fun detachView() {
        reducer.clearDispose()
        disposeContainer.clear()
    }

    override fun bind() {
        view?.clickLogin?.subscribe{
            reducer.login(it.first, it.second)
        }?.addTo(disposeContainer)

        reducer.updateDestination
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.navigateTo(it)
            }.addTo(disposeContainer)

        reducer.updateError
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.showError(it)
            }.addTo(disposeContainer)
    }
}