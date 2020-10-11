package com.example.shelter.presentation.onBoarding.login2.reducer

import com.example.shelter.presentation.checkEmailAndPassword
import com.example.shelter.presentation.onBoarding.login2.model.LoginDestination
import com.example.shelter.presentation.onBoarding.login2.model.LoginErrorCode
import com.example.shelter.presentation.onBoarding.login2.model.LoginException
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LoginReducer @Inject constructor(): ILoginReducer{

    override val updateDestination: PublishSubject<LoginDestination> = PublishSubject.create()
    override val updateError: PublishSubject<LoginException> = PublishSubject.create()

    private val disposeContainer = CompositeDisposable()

    override fun login(email: String, password: String) {
        if(checkEmailAndPassword(email, password)){
            //repository
            updateDestination.onNext(LoginDestination.NEWS_SCREEN)
        }else{
            updateError.onNext(LoginException(LoginErrorCode.REQUEST_STATUS_ERROR, ""))
        }
    }

    override fun clearDispose() {
        disposeContainer.clear()
    }
}