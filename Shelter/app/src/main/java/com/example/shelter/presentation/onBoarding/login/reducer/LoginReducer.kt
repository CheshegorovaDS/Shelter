package com.example.shelter.presentation.onBoarding.login.reducer

import com.example.shelter.utils.checkEmailAndPassword
import com.example.shelter.presentation.onBoarding.login.model.LoginDestination
import com.example.shelter.presentation.onBoarding.login.model.LoginErrorCode
import com.example.shelter.presentation.onBoarding.login.model.LoginException
import com.example.shelter.presentation.onBoarding.registration.model.User
import com.example.shelter.presentation.storage.LoggedUserProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LoginReducer @Inject constructor(
    var loggedUser: LoggedUserProvider
): ILoginReducer{

    //var repository: IUserRepository = UserRepository()

    override val updateDestination: PublishSubject<LoginDestination> = PublishSubject.create()
    override val updateError: PublishSubject<LoginException> = PublishSubject.create()

    private val disposeContainer = CompositeDisposable()

    override fun login(email: String, password: String) {
        if(checkEmailAndPassword(email, password)){
            //repository
            loggedUser.setLoggedUser(User(
                email = email,
                password = password))
            updateDestination.onNext(LoginDestination.NEWS_SCREEN)
        }else{
            updateError.onNext(LoginException(LoginErrorCode.REQUEST_STATUS_ERROR, ""))
        }
    }

    override fun clearDispose() {
        disposeContainer.clear()
    }
}
