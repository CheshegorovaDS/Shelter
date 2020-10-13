package com.example.shelter.presentation.log_in_app.reducer

import com.example.shelter.presentation.log_in_app.model.LogInAppDestination
import com.example.shelter.presentation.log_in_app.model.LogInAppState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LogInAppReducer @Inject constructor(): ILogInAppReducer {

    override val updateDestination: PublishSubject<LogInAppDestination> = PublishSubject.create()
    override val updateState: PublishSubject<LogInAppState> = PublishSubject.create()
    private val disposeContainer = CompositeDisposable()

    private var state = LogInAppState()

    override fun login() {
        state = state.copy(loginEnabled = false, registrationEnabled = false)
        updateState.onNext(state)
        updateDestination.onNext(LogInAppDestination.LOGIN_SCREEN)
    }

    override fun registration() {
        state = state.copy(loginEnabled = false, registrationEnabled = false)
        updateState.onNext(state)
        updateDestination.onNext(LogInAppDestination.REGISTRATION_SCREEN)
    }

    override fun clearDispose() {
        disposeContainer.clear()
    }


}