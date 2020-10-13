package com.example.shelter.presentation.log_in_app.reducer

import com.example.shelter.presentation.base.inrefaces.BaseReducer
import com.example.shelter.presentation.log_in_app.model.LogInAppDestination
import com.example.shelter.presentation.log_in_app.model.LogInAppState
import io.reactivex.subjects.PublishSubject

interface ILogInAppReducer: BaseReducer {
    val updateDestination: PublishSubject<LogInAppDestination>
    val updateState: PublishSubject<LogInAppState>
    fun login()
    fun registration()
}
