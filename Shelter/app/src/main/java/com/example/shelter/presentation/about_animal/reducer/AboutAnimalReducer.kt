package com.example.shelter.presentation.about_animal.reducer

import com.example.shelter.data.news.repository.INewsRepository
import com.example.shelter.presentation.about_animal.model.AboutAnimalException
import com.example.shelter.presentation.about_animal.model.AboutAnimalState
import com.example.shelter.presentation.model.News
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class AboutAnimalReducer @Inject constructor(
    private var backend: INewsRepository
): IAboutAnimalReducer {

    private val dispose = CompositeDisposable()

    override val updateNews: PublishSubject<News> = PublishSubject.create()
    override val updateState: PublishSubject<AboutAnimalState> = PublishSubject.create()
    override val updateException: PublishSubject<AboutAnimalException> = PublishSubject.create()
    override val updateDestination: PublishSubject<Int> = PublishSubject.create()

    var state = AboutAnimalState()
    var news: News? = null

    override fun downloadNews(idAnimal: Int) {
        updateState.onNext(state)

        dispose.add(backend.getNewsById(idAnimal)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    news = it
                    updateNews.onNext(news!!)
                    state = AboutAnimalState(
                        progressbarVisibility = false,
                        nameVisibility = true,
                        photoVisibility = true,
                        ageVisibility = (it.age != 0),
                        breedVisibility = (it.breed != null),
                        animalType = true,
                        sexVisibility = true,
                        categoryVisibility = true,
                        passportVisibility = (it.passport != null),
                        descriptionVisibility = (it.description != null),
                        userVisibility = true
                    )
                    updateState.onNext(state)
                }, {
                    updateException.onNext(AboutAnimalException())
                })
        )

    }

    override fun openUserPage() {
        news?.user?.id?.let { updateDestination.onNext(it) }
    }

    override fun clearDispose() = dispose.clear()

}
