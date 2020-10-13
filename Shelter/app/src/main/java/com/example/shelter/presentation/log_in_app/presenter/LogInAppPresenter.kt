package com.example.shelter.presentation.log_in_app.presenter


import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.log_in_app.model.LogInAppState
import com.example.shelter.presentation.log_in_app.reducer.ILogInAppReducer
import com.example.shelter.presentation.log_in_app.view.LogInAppView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class LogInAppPresenter @Inject constructor(
    var reducer: ILogInAppReducer
): ILogInAppPresenter {

    var view: LogInAppView? = null

    private val disposeContainer = CompositeDisposable()

    override fun attachView(baseView: BaseView) {
        view = baseView as? LogInAppView
        bind()
    }

    override fun detachView() {
        disposeContainer.clear()
        reducer.clearDispose()
    }

    override fun bind() {
        view?.clickRegistration()?.subscribe {
            reducer.registration()
        }?.addTo(disposeContainer)

        view?.clickLogin()?.subscribe{
            reducer.login()
        }?.addTo(disposeContainer)

        reducer.updateDestination
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.navigationTo(it)
            }.addTo(disposeContainer)

        reducer.updateState
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                updateState(it)
            }.addTo(disposeContainer)
    }

    private fun updateState(state: LogInAppState){
        view?.loginEnabled(state.loginEnabled)
        view?.registrationEnabled(state.registrationEnabled)
    }

}
