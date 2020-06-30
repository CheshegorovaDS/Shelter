package com.example.shelter.presentation.login.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.shelter.R
import com.example.shelter.presentation.APP_PREFERENCES
import com.example.shelter.presentation.extention.nextActivity
import com.example.shelter.presentation.extention.toast
import com.example.shelter.presentation.login.presenter.ILoginPresenter
import com.example.shelter.presentation.login.presenter.LoginPresenter
import com.example.shelter.presentation.menu.MenuActivity
import com.example.shelter.presentation.model.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.toolbar

class LoginActivity: AppCompatActivity(), LoginView {
    lateinit var sharedPreferences:SharedPreferences
    lateinit var presenter:ILoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        sharedPreferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)

        initView()

        btnLogIn.setOnClickListener{ view ->
            presenter.login(User(email.text.toString(),password.text.toString()))
        }

    }

    fun initView(){
        presenter = LoginPresenter(this,sharedPreferences)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_back, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showError() {
        toast("Неправильно введены данные.")
    }

    override fun login() {
        nextActivity(MenuActivity::class.java)
    }
}