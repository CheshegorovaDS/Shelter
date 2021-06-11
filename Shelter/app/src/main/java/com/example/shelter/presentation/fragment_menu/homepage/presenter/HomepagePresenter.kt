package com.example.shelter.presentation.fragment_menu.homepage.presenter

import com.example.shelter.presentation.base.inrefaces.BasePresenter
import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.fragment_menu.homepage.model.HomepageState
import com.example.shelter.presentation.fragment_menu.homepage.reducer.IHomepageReducer
import com.example.shelter.presentation.fragment_menu.homepage.view.HomepageView
import com.example.shelter.presentation.model.User
import com.example.shelter.presentation.onBoarding.registration.model.UserType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
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
        view?.downloadUser?.subscribe {
            reducer.downloadUser()
        }?.addTo(disposeContainer)

        view?.clickLogout?.subscribe{
            reducer.logout()
        }?.addTo(disposeContainer)

        view?.clickEditCard?.subscribe {
            reducer.editCard(it)
        }?.addTo(disposeContainer)

        view?.clickDeleteCard?.subscribe {
            reducer.deleteCard(it)
        }?.addTo(disposeContainer)

        reducer.updateState
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                renderState(it)
            }.addTo(disposeContainer)

        reducer.updateUser
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.human != null) {
                    updateHuman(it)
                } else {
                    updateOrganisation(it)
                }
            }.addTo(disposeContainer)

        reducer.updateNews
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.updateCards(it)
            }.addTo(disposeContainer)

        reducer.updateDestination
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.exit()
            }.addTo(disposeContainer)
    }

    private fun updateHuman(user: User) {
        val human = user.human ?: return
        val name = "${human.lastName} ${human.firstName} ${human.patronymic ?: ""}"
        view?.updateInfo(name, UserType.HUMAN, user.email, user.phone)
    }

    private fun updateOrganisation(user: User) {
        val organisation = user.organisation ?: return
        view?.updateInfo(organisation.title, UserType.ORGANIZATION, user.email, user.phone)
    }

    private fun renderState(state: HomepageState) {
        view?.showProgressBar(state.progressbarVisibility)
        view?.showCards(state.listNewsVisibility)
        view?.showListIsEmpty(state.listIsEmptyText)
        view?.showUserInfo(state.userInfoVisibility)
    }
}