package com.example.shelter.presentation.news.reducer

import android.content.Intent
import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.news.model.NewsDestination
import com.example.shelter.presentation.news.model.NewsException
import com.example.shelter.presentation.news.model.NewsState
import com.example.shelter.presentation.news.utils.convertAnimalToIntent
import com.example.shelter.presentation.storage.LoggedUserProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class NewsReducer @Inject constructor(
    var loggedUserProvider: LoggedUserProvider
) : INewsReducer {

    private val disposeContainer = CompositeDisposable()

    override val updateNews: PublishSubject<List<Animal>> = PublishSubject.create()
    override val updateState: PublishSubject<NewsState> = PublishSubject.create()
    override val updateException: PublishSubject<NewsException> = PublishSubject.create()
    override val openAnimalNews: PublishSubject<Intent> = PublishSubject.create()
    override val updateDestination: PublishSubject<NewsDestination> = PublishSubject.create()

    private var state = NewsState()
    private var list : List<Animal> = listOf()

    override fun downloadNews() {
        state = NewsState(progressBarVisibility = true)
        updateState.onNext(state)
//        downloadNews from service
        list = addList()
        updateNews.onNext(list)
        state = state.copy(
            progressBarVisibility = false,
            addNewsEnabled = true
        )
        updateState.onNext(state)
        //or
//        updateException.onNext()
    }

    override fun downloadNews(category: String) {
        TODO("Not yet implemented")
    }

    override fun openFilter() {
        TODO("Not yet implemented")
    }

    override fun openCard(animal: Animal) {
        state = state.copy(addNewsEnabled = false)
        updateState.onNext(state)
        openAnimalNews.onNext(convertAnimalToIntent(animal))
    }

    override fun addNews() {
        if (userIsLogged()) {
            updateDestination.onNext(NewsDestination.ADD_ANIMAL_CARD)
        } else {
            updateDestination.onNext(NewsDestination.LOGIN_OR_REGISTRATION_SCREEN)
        }
    }

    private fun userIsLogged(): Boolean = loggedUserProvider.getLoggedUser() != null

    override fun clearDispose() {
        disposeContainer.clear()
    }

    private fun addList(): List<Animal> {
        val tables = ArrayList<Animal>()
        tables.add(Animal("Вася", ""))
        tables.add(Animal("Буся", "Ж",  age = 2))
        tables.add(Animal("Meca","Ж",age = 1))
        tables.add(Animal("Муся","Ж",age = 1))
        tables.add(Animal("Симбад","М",age = 1))
        tables.add(Animal("Миша","М",age = 2))
        return tables
    }
}