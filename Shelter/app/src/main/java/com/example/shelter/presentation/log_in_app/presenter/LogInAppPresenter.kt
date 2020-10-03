package com.example.shelter.presentation.log_in_app.presenter


import com.example.shelter.presentation.base2.BaseView
import com.example.shelter.presentation.log_in_app.reducer.ILogInAppReducer
import com.example.shelter.presentation.log_in_app.reducer.LogInAppReducer
import com.example.shelter.presentation.log_in_app.view.LogInAppView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class LogInAppPresenter: ILogInAppPresenter {
    var view: LogInAppView
    private val reducer: ILogInAppReducer = LogInAppReducer()

    private val disposeContainer = CompositeDisposable()

    constructor(view:LogInAppView){
        this.view = view
        bind()
    }

    override fun login() {
        view.openLogin()
    }

    override fun registration() {
        view.openRegistration()
    }

    override fun attachView(baseView: BaseView) {

    }

    override fun detachView() {
        disposeContainer.clear()
        reducer.clearDispose()
    }

    override fun bind() {
        view.clickRegistration().subscribe {
            reducer.registration()
        }.addTo(disposeContainer)

        view.clickLogin().subscribe{
            reducer.login()
        }.addTo(disposeContainer)

        reducer.updateDestination
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.navigationTo(it)
            }.addTo(disposeContainer)
    }
}