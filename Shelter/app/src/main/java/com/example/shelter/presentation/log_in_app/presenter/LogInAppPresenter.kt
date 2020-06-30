package com.example.shelter.presentation.log_in_app.presenter


import com.example.shelter.presentation.log_in_app.view.LogInAppView

class LogInAppPresenter: ILogInAppPresenter {
    var view:LogInAppView

    constructor(view:LogInAppView){
        this.view = view
    }

    override fun login() {
        view.openLogin()
    }

    override fun registration() {
        view.openRegistration()
    }
}