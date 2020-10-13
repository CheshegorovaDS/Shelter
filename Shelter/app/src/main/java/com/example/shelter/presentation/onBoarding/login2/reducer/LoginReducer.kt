package com.example.shelter.presentation.onBoarding.login2.reducer

import com.example.shelter.presentation.checkEmailAndPassword
import com.example.shelter.presentation.model.User
import com.example.shelter.presentation.onBoarding.login2.model.LoginDestination
import com.example.shelter.presentation.onBoarding.login2.model.LoginErrorCode
import com.example.shelter.presentation.onBoarding.login2.model.LoginException
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
            loggedUser.setLoggedUser(User(email, password))
            updateDestination.onNext(LoginDestination.NEWS_SCREEN)
        }else{
            updateError.onNext(LoginException(LoginErrorCode.REQUEST_STATUS_ERROR, ""))
        }
    }

    override fun clearDispose() {
        disposeContainer.clear()
    }
}
