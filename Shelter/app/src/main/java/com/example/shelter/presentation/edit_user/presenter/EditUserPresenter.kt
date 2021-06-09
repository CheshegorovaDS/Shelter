package com.example.shelter.presentation.edit_user.presenter

import com.example.shelter.presentation.base.inrefaces.BasePresenter
import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.edit_user.model.EditUserState
import com.example.shelter.presentation.edit_user.reducer.IEditUserReducer
import com.example.shelter.presentation.edit_user.view.EditUserActivity
import com.example.shelter.presentation.edit_user.view.EditUserView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class EditUserPresenter @Inject constructor(
    private var reducer: IEditUserReducer
): BasePresenter {

    private var view: EditUserView? = null
    private val dispose = CompositeDisposable()

    override fun attachView(baseView: BaseView) {
        view = baseView as EditUserActivity
        bind()
    }

    override fun detachView() {
        reducer.clearDispose()
        dispose.clear()
    }

    override fun bind() {
        view?.downloadInfo?.subscribe {
            reducer.downloadInfo()
        }?.addTo(dispose)

        reducer.updateState
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                renderState(it)
            }.addTo(dispose)

        reducer.updateUser
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.human != null) {
                    view?.setHumanInfo(it)
                } else {
                    view?.setOrganisationInfo(it)
                }
            }.addTo(dispose)
    }

    private fun renderState(state: EditUserState) {
        view?.showProgressBar(state.progressBarVisibility)
        view?.showException(state.exceptionVisibility)
        view?.showApply(state.applyVisibility)
        view?.enabledApply(state.applyEnabled)
        if (state.userFieldsVisibility) {
            state.userType?.let { view?.showFields(it) }
        } else {
            view?.hideFields()
        }
    }
}