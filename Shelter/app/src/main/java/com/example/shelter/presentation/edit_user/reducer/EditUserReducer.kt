package com.example.shelter.presentation.edit_user.reducer

import com.example.shelter.data.user.repositry.IUserRepository
import com.example.shelter.presentation.edit_user.model.EditUserDestination
import com.example.shelter.presentation.edit_user.model.EditUserException
import com.example.shelter.presentation.edit_user.model.EditUserState
import com.example.shelter.presentation.model.User
import com.example.shelter.presentation.storage.LoggedUserProvider
import io.reactivex.disposables.CompositeDisposable
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

    override fun downloadInfo() {

    }

    override fun clearDispose() = dispose.clear()
}