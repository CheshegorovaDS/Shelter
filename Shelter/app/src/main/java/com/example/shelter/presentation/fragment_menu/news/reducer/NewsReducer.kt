package com.example.shelter.presentation.fragment_menu.news.reducer

import com.example.shelter.data.news.repository.INewsRepository
import com.example.shelter.presentation.fragment_menu.news.model.FilterNews
import com.example.shelter.presentation.fragment_menu.news.model.NewsDestination
import com.example.shelter.presentation.fragment_menu.news.model.NewsException
import com.example.shelter.presentation.fragment_menu.news.model.NewsState
import com.example.shelter.presentation.model.News
import com.example.shelter.presentation.storage.LoggedUserProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class NewsReducer @Inject constructor(
    private var loggedUserProvider: LoggedUserProvider,
    private var backend: INewsRepository
) : INewsReducer {

    private val disposeContainer = CompositeDisposable()

    override val updateNews: PublishSubject<List<News>> = PublishSubject.create()
    override val updateState: PublishSubject<NewsState> = PublishSubject.create()
    override val updateException: PublishSubject<NewsException> = PublishSubject.create()
    override val openAnimalNews: PublishSubject<Int> = PublishSubject.create()
    override val updateDestination: PublishSubject<NewsDestination> = PublishSubject.create()

    private var state = NewsState()
    private var list : MutableList<News> = mutableListOf()

    override fun downloadNews() {
        state = NewsState(progressBarVisibility = true)
        updateState.onNext(state)

        disposeContainer.add(
            backend.getListNews()
            .subscribeOn(Schedulers.io())
            .subscribe ({
                list.clear()
                list.addAll(it)
                updateNews.onNext(list)
            }, {
                updateException.onNext(NewsException())
            })
        )

        state = state.copy(
            progressBarVisibility = false,
            listVisibility = true,
            addNewsEnabled = true
        )
        updateState.onNext(state)
    }

    override fun downloadNews(request: String) {
        state = NewsState(progressBarVisibility = true)
        updateState.onNext(state)

        disposeContainer.add(
            backend.getListNewsByString(request)
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    list.clear()
                    list.addAll(it)
                    updateNews.onNext(list)
                }, {
                    updateException.onNext(NewsException())
                })
        )

        state = NewsState(
            progressBarVisibility = false,
            listVisibility = true,
            addNewsEnabled = true
        )
        updateState.onNext(state)
    }

    override fun downloadNews(filters: FilterNews) {
        state = NewsState(progressBarVisibility = true)
        updateState.onNext(state)

        disposeContainer.add(
            backend.getListNewsByFilters(filters)
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    list.clear()
                    list.addAll(it)
                    updateNews.onNext(list)
                }, {
                    updateException.onNext(NewsException())
                })
        )

        state = state.copy(
            progressBarVisibility = false,
            listVisibility = true,
            addNewsEnabled = true
        )
        updateState.onNext(state)
    }

    override fun openFilter() = updateDestination.onNext(NewsDestination.FILTER_CARD)

    override fun openSearch() {
//        state = state.copy(
//            focusSearch = true
//        )
//        updateState.onNext(state)
    }

    override fun updateSearch(text: String) {
        state = if (text == "") {
            state.copy(
                filterVisibility = true,
                searchVisibility = true,
                enterVisibility = false,
                backVisibility = false
            )
        } else {
            state.copy(
                enterVisibility = true,
                backVisibility =  true,
                filterVisibility = false,
                searchVisibility = false
            )
        }
        updateState.onNext(state)
    }

    override fun openCard(news: News) {
        state = state.copy(addNewsEnabled = false)
        updateState.onNext(state)
        openAnimalNews.onNext(news.id)
    }

    override fun addNews() =
        if (userIsLogged()) {
            updateDestination.onNext(NewsDestination.ADD_ANIMAL_CARD)
        } else {
            updateDestination.onNext(NewsDestination.LOGIN_OR_REGISTRATION_SCREEN)
        }

    private fun userIsLogged(): Boolean = loggedUserProvider.getToken() != null

    override fun clearDispose() = disposeContainer.clear()
}
