package com.example.shelter.presentation.onBoarding.registration2.presenter

import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.onBoarding.registration2.model.RegistrationState
import com.example.shelter.presentation.onBoarding.registration2.reducer.IRegistrationReducer
import com.example.shelter.presentation.onBoarding.registration2.reducer.RegistrationReducer
import com.example.shelter.presentation.onBoarding.registration2.view.RegistrationFragment
import com.example.shelter.presentation.onBoarding.registration2.view.RegistrationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class RegistrationPresenter @Inject constructor(
    var reducer: IRegistrationReducer
): IRegistrationPresenter {

    var view: RegistrationView? = null

    private val disposeContainer = CompositeDisposable()

    override fun attachView(baseView: BaseView) {
        view = baseView as? RegistrationFragment
        bind()
    }

    override fun detachView() {
        reducer.clearDispose()
        disposeContainer.clear()
    }

    override fun bind() {
        view?.updateUserType?.subscribe {
            reducer.updateUserType(it)
        }?.addTo(disposeContainer)

        view?.clickRegistration?.subscribe {
            reducer.tryToRegistration(it)
        }?.addTo(disposeContainer)

        reducer.updateState
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                renderState(it)
            }.addTo(disposeContainer)

        reducer.updateException
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.showError(it)
            }.addTo(disposeContainer)

        reducer.updateDestination
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.navigateTo(it)
            }.addTo(disposeContainer)
    }

    private fun renderState(state: RegistrationState) {
        view?.setUserType(state.userType)
        view?.setFirstNameVisible(state.firstNameVisibility)
        view?.setLastNameVisible(state.lastNameVisibility)
        view?.setOrganisationNameVisible(state.organizationNameVisibility)
    }
}