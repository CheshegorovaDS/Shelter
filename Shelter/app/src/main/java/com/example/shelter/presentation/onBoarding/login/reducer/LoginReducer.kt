package com.example.shelter.presentation.onBoarding.login.reducer

import com.example.shelter.data.user.repositry.IUserRepository
import com.example.shelter.utils.checkEmailAndPassword
import com.example.shelter.presentation.onBoarding.login.model.LoginDestination
import com.example.shelter.presentation.onBoarding.login.model.LoginErrorCode
import com.example.shelter.presentation.onBoarding.login.model.LoginException
import com.example.shelter.presentation.onBoarding.registration.model.User
import com.example.shelter.presentation.storage.LoggedUserProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LoginReducer @Inject constructor(
    var loggedUser: LoggedUserProvider,
    var userRepository: IUserRepository
): ILoginReducer{

    override val updateDestination: PublishSubject<LoginDestination> = PublishSubject.create()
    override val updateError: PublishSubject<LoginException> = PublishSubject.create()

    private val disposeContainer = CompositeDisposable()

    override fun login(email: String, password: String) {
        if(checkEmailAndPassword(email, password)){

            disposeContainer.add(
                userRepository.login(email, password)
                    .subscribeOn(Schedulers.io())
                    .subscribe ({
                        loggedUser.setLoggedUser(
                            User(
                            email = email,
                            password = password
                        )
                        )
                        loggedUser.setId(it.first)
                        loggedUser.setToken(it.second)
                        updateDestination.onNext(LoginDestination.NEWS_SCREEN)
                    }, {
//                        updateException.onNext(NewsException())
                    })
            )
        }else{
            updateError.onNext(LoginException(LoginErrorCode.REQUEST_STATUS_ERROR, ""))
        }
    }

    override fun clearDispose() {
        disposeContainer.clear()
    }
}
