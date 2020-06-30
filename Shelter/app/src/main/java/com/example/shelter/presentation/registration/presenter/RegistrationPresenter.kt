package com.example.shelter.presentation.registration.presenter

import android.content.SharedPreferences
import com.example.shelter.presentation.APP_LOGIN
import com.example.shelter.presentation.checkEmailAndPassword
import com.example.shelter.presentation.model.User
import com.example.shelter.presentation.registration.view.RegistrationView
import java.util.regex.Pattern

class RegistrationPresenter : IRegistrationPresenter{
    var view: RegistrationView
    var sharedPreferences:SharedPreferences

    constructor(view: RegistrationView, sharedPreferences: SharedPreferences){
        this.view = view
        this.sharedPreferences = sharedPreferences
    }

    override fun register(user: User) {
        if (checkDataUser(user)) {
                saveUser()
               view.navigateTo()
            }else{
                view.showException()
            }
    }

    override fun showListUser() {
        view.showListTypeUser()
    }

    private fun checkDataUser(user:User): Boolean{
        val pattern: Pattern = Pattern.compile("^[+а-яА-Я]+$")
        val phonePattern: Pattern = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}\$")
        val str =  user.type
        if(!checkEmailAndPassword(user) ||
            user.type.equals("") ||
            user.phone.equals("")||
            !phonePattern.matcher(user.phone).find() ||
            !pattern.matcher(user.city).find() ||
            (user.type.equals("Частное лицо") && !view.checkHuman()) ||
            (user.type.equals("Организация") && !view.checkOrganisation())

        ){
            return false
        }

        return true
    }

    private fun saveUser(){
        var ed: SharedPreferences.Editor = sharedPreferences.edit()
        ed.putString(APP_LOGIN,"true")
        ed.commit()
    }


}