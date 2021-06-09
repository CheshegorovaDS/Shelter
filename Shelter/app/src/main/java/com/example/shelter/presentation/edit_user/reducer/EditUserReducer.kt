package com.example.shelter.presentation.edit_user.reducer

import com.example.shelter.data.user.repositry.IUserRepository
import com.example.shelter.presentation.edit_user.model.EditUserDestination
import com.example.shelter.presentation.edit_user.model.EditUserException
import com.example.shelter.presentation.edit_user.model.EditUserState
import com.example.shelter.presentation.filtering_news.model.FilteringNewsException
import com.example.shelter.presentation.model.User
import com.example.shelter.presentation.onBoarding.registration.model.UserType
import com.example.shelter.presentation.storage.LoggedUserProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class EditUserReducer @Inject constructor(
    private var loggedUserProvider: LoggedUserProvider,
    private var userRepository: IUserRepository
): IEditUserReducer {

    override val updateState: PublishSubject<EditUserState> = PublishSubject.create()
    override val updateException: PublishSubject<EditUserException> = PublishSubject.create()
    override val updateUser: PublishSubject<User> = PublishSubject.create()
    override val updateDestination: PublishSubject<EditUserDestination> = PublishSubject.create()

    private val dispose = CompositeDisposable()
    private var state = EditUserState()
    private var user: User? = null

    override fun downloadInfo() {
        state = EditUserState()
        updateState.onNext(state)

        val id = loggedUserProvider.getId()
        if (id == null) {
            updateException.onNext(EditUserException())
            return
        }

        dispose.add(
            userRepository.getUserById(id)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    user = it
                    updateUser.onNext(it)

                    val type = if (it.human != null) {
                        UserType.HUMAN
                    } else {
                        UserType.ORGANIZATION
                    }

                    state = state.copy(
                        userFieldsVisibility = true,
                        userType = type,
                        progressBarVisibility = false,
                        applyVisibility = true
                    )
                    updateState.onNext(state)
                }, {
                    updateException.onNext(EditUserException())
                    state = EditUserState(
                        exceptionVisibility = true,
                        progressBarVisibility = false
                    )
                    updateState.onNext(state)
                })
        )
    }

    override fun clearDispose() = dispose.clear()
}