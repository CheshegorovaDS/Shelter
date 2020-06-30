package com.example.shelter.presentation.login.presenter

import android.content.SharedPreferences
import com.example.shelter.presentation.APP_LOGIN
import com.example.shelter.presentation.checkEmailAndPassword
import com.example.shelter.presentation.login.view.LoginView
import com.example.shelter.presentation.model.User

class LoginPresenter: ILoginPresenter {
    var view:LoginView
    var sharedPreferences:SharedPreferences

    constructor(view: LoginView, sharedPreferences: SharedPreferences){
        this.view = view
        this.sharedPreferences = sharedPreferences
    }

    override fun login(user: User) {
        if(checkEmailAndPassword(user)){
            saveUser()
            view.login()
        }else{
            view.showError()
        }
    }

    fun saveUser(){
        var ed: SharedPreferences.Editor = sharedPreferences.edit()
        ed.putString(APP_LOGIN,"true")
        ed.commit()
    }


}