package com.example.shelter.presentation.about_animal.reducer

import com.example.shelter.data.news.repository.INewsRepository
import com.example.shelter.data.user.repositry.UserRepository
import com.example.shelter.presentation.about_animal.model.AboutAnimalException
import com.example.shelter.presentation.about_animal.model.AboutAnimalState
import com.example.shelter.presentation.model.News
import com.example.shelter.presentation.model.User
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class AboutAnimalReducer @Inject constructor(
    private var newsRepository: INewsRepository,
    private var userRepository: UserRepository
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

        dispose.add(newsRepository.getNewsById(idAnimal)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    news = it
                    downloadUser(news!!.idUser)
                }, {
                    updateException.onNext(AboutAnimalException())
                })
        )

    }

    private fun downloadUser(idUser: Int) {
        dispose.add(userRepository.getUserById(idUser)
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (news == null) {
                    return@subscribe
                }
                news = news!!.copy(
                    user = it
                )
                updateNews.onNext(news!!)
                state = AboutAnimalState(
                    progressbarVisibility = false,
                    nameVisibility = true,
                    photoVisibility = true,
                    ageVisibility = (news!!.age != 0),
                    breedVisibility = (news!!.breed != null),
                    animalType = true,
                    sexVisibility = true,
                    categoryVisibility = true,
                    passportVisibility = (news!!.passport != null),
                    descriptionVisibility = (news!!.description != null),
                    userVisibility = true
                )
                updateState.onNext(state)
            }, {
                updateException.onNext(AboutAnimalException())
            })
        )
    }

    override fun openUserPage() {
        news?.idUser?.let { updateDestination.onNext(it) }
    }

    override fun clearDispose() = dispose.clear()

}
