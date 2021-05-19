package com.example.shelter.presentation.about_animal.presenter

import com.example.shelter.presentation.about_animal.model.AboutAnimalState
import com.example.shelter.presentation.about_animal.reducer.IAboutAnimalReducer
import com.example.shelter.presentation.about_animal.view.AboutAnimalView
import com.example.shelter.presentation.base.inrefaces.BasePresenter
import com.example.shelter.presentation.base.inrefaces.BaseView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class AboutAnimalPresenter @Inject constructor(
    private var reducer: IAboutAnimalReducer
): BasePresenter {

    var view: AboutAnimalView? = null

    private val disposeContainer = CompositeDisposable()

    override fun attachView(baseView: BaseView) {
        view = baseView as? AboutAnimalView
        bind()
    }

    override fun detachView() {
        reducer.clearDispose()
        disposeContainer.clear()
    }

    override fun bind() {
        view?.downloadNews?.subscribe {
            reducer.downloadNews(it)
        }?.addTo(disposeContainer)

        view?.clickUserCard()?.subscribe{
            reducer.openUserPage()
        }?.addTo(disposeContainer)

        reducer.updateState
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                renderState(it)
            }.addTo(disposeContainer)

        reducer.updateNews
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.updateNews(it)
            }.addTo(disposeContainer)

        reducer.updateDestination
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.openUserHomepage(it)
            }.addTo(disposeContainer)
    }

    private fun renderState(state: AboutAnimalState) {
        view?.showName(state.nameVisibility)
        view?.showPhoto(state.photoVisibility)
        view?.showAge(state.ageVisibility)
        view?.showSex(state.sexVisibility)
        view?.showBreed(state.breedVisibility)
        view?.showPassport(state.passportVisibility)
        view?.showDescription(state.descriptionVisibility)
        view?.showUser(state.userVisibility)
        view?.showProgressBar(state.progressbarVisibility)
    }
}