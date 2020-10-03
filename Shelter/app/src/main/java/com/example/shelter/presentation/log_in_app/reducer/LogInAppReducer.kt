package com.example.shelter.presentation.log_in_app.reducer

import com.example.shelter.presentation.log_in_app.model.LogInAppDestination
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class LogInAppReducer: ILogInAppReducer {

    override val updateDestination: PublishSubject<LogInAppDestination> = PublishSubject.create()
    private val disposeContainer = CompositeDisposable()

    override fun login() {
        updateDestination.onNext(LogInAppDestination.LOGIN_SCREEN)
    }

    override fun registration() {
        updateDestination.onNext(LogInAppDestination.REGISTRATION_SCREEN)
    }

    override fun clearDispose() {
        disposeContainer.clear()
    }


}