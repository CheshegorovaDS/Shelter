package com.example.shelter.presentation.fragment_menu.homepage.reducer

import com.example.shelter.data.news.repository.INewsRepository
import com.example.shelter.data.user.repositry.IUserRepository
import com.example.shelter.presentation.fragment_menu.homepage.model.HomepageDestination
import com.example.shelter.presentation.fragment_menu.homepage.model.HomepageException
import com.example.shelter.presentation.fragment_menu.homepage.model.HomepageState
import com.example.shelter.presentation.fragment_menu.news.model.NewsException
import com.example.shelter.presentation.model.News
import com.example.shelter.presentation.model.User
import com.example.shelter.presentation.onBoarding.login.model.LoginDestination
import com.example.shelter.presentation.storage.LoggedUserProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class HomepageReducer @Inject constructor(
    private var loggedUserProvider: LoggedUserProvider,
    private var newsRepository: INewsRepository,
    private var userRepository: IUserRepository
): IHomepageReducer {

    override val updateUser: PublishSubject<User> = PublishSubject.create()
    override val updateNews: PublishSubject<List<News>> = PublishSubject.create()
    override val updateState: PublishSubject<HomepageState> = PublishSubject.create()
    override val updateException: PublishSubject<HomepageException> = PublishSubject.create()
    override val updateDestination: PublishSubject<HomepageDestination> = PublishSubject.create()

    private val dispose = CompositeDisposable()
    private var state = HomepageState()
    private var user: User? = null
    private val list = mutableListOf<News>()

    override fun downloadUser() {
        state = HomepageState()
        updateState.onNext(state)

        if (loggedUserProvider.getId() == null) {
            return
        }

        dispose.addAll(
            userRepository.getUserById(loggedUserProvider.getId()!!)
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    user = it
                    updateUser.onNext(it)
                    state = state.copy(userInfoVisibility = true)
                    updateState.onNext(state)
                }, {
//                        updateException.onNext(NewsException())
                }),
            newsRepository.getNewsByUser(loggedUserProvider.getId()!!)
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    list.addAll(it)
                    if (list.isNotEmpty()) {
                        updateNews.onNext(it.toList())
                    }

                    state = state.copy(
                        listNewsVisibility = list.isNotEmpty(),
                        listIsEmptyText = list.isEmpty(),
                        progressbarVisibility = false
                    )
                    updateState.onNext(state)
                }, {
//                        updateException.onNext(NewsException())
                })
        )
    }

    override fun logout() {
        val token = loggedUserProvider.getToken()
        if (token == null){
            updateException.onNext(HomepageException())
            return
        }

        dispose.add(
            userRepository.logout(token)
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    loggedUserProvider.logout()
                    updateDestination.onNext(HomepageDestination.NEWS_FRAGMENT)
                }, {
                        updateException.onNext(HomepageException())
                })
        )
    }

    override fun clearDispose() = dispose.clear()
}
