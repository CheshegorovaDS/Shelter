package com.example.shelter.presentation.fragment_menu.homepage.reducer

import com.example.shelter.data.news.repository.INewsRepository
import com.example.shelter.data.user.repositry.IUserRepository
import com.example.shelter.presentation.storage.LoggedUserProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomepageReducer @Inject constructor(
    private var loggedUserProvider: LoggedUserProvider,
    private var newsRepository: INewsRepository,
    private var userRepository: IUserRepository
): IHomepageReducer {

    private val dispose = CompositeDisposable()

    override fun clearDispose() = dispose.clear()
}