package com.example.shelter.presentation.onBoarding.registration.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.shelter.R
import com.example.shelter.presentation.APP_PREFERENCES
import com.example.shelter.presentation.extention.nextActivity
import com.example.shelter.presentation.extention.toast
import com.example.shelter.presentation.menu.MenuActivity
import com.example.shelter.presentation.model.User
import com.example.shelter.presentation.onBoarding.registration.presenter.IRegistrationPresenter
import com.example.shelter.presentation.onBoarding.registration.presenter.RegistrationPresenter
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.fragment_registration.*
import java.util.regex.Pattern

class RegistrationActivity: AppCompatActivity(),
    RegistrationView {

    lateinit var presenter: IRegistrationPresenter
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_registration)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        initView()

        editTxtYouAre.setOnClickListener { presenter.showListUser()}

        registration.setOnClickListener{ view ->
            presenter.register(User(email.text.toString(),
                                    password.text.toString(),
                                    editTxtYouAre.text.toString(),
                                    phone.text.toString(),
                                    city.text.toString()
            ))
        }
    }

    private fun initView(){
        presenter =
            RegistrationPresenter(
                this,
                sharedPreferences
            )
    }

    override fun showListTypeUser(){
        val list = arrayOf("Частное лицо", "Организация")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Вы : ").setCancelable(true)
            // добавляем одну кнопку для закрытия диалога
            .setNegativeButton("Назад") { dialog, id -> dialog.cancel() }
            //переключатели
        var user: String = ""
            builder.setSingleChoiceItems(list, -1) {
                    dialog, item -> user = list[item]
            }
        builder.setPositiveButton("OK") { dialog, id ->
            if(user.equals("Частное лицо")){
                lastName.setVisibility(View.VISIBLE);
                firstName.setVisibility(View.VISIBLE);
                patronymic.setVisibility(View.VISIBLE);
                organisationName.setVisibility(View.GONE)

            }else{
                lastName.setVisibility(View.GONE)
                firstName.setVisibility(View.GONE);
                patronymic.setVisibility(View.GONE);
                organisationName.setVisibility(View.VISIBLE)
            }
            editTxtYouAre.setText(user)
        }


        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun navigateTo() {
        nextActivity(MenuActivity::class.java)
    }

    override fun showException() {
        toast("Неправильно введены данные")
    }

    override fun checkOrganisation():Boolean{
        val pattern: Pattern = Pattern.compile("^[+а-яА-Я]+$")
        if(!pattern.matcher(organisationName.text).find()){
            return false
        }
        return true
    }

    override fun checkHuman():Boolean{
        val pattern: Pattern = Pattern.compile("^[+а-яА-Я]+$")
        val str =  editTxtYouAre.text.toString()
        if(!pattern.matcher(lastName.text).find() ||
            !pattern.matcher(firstName.text).find() ||
            editTxtYouAre.text.toString().equals("") ||
            (!patronymic.text.toString().equals("") && !pattern.matcher(patronymic.text).find())
        ){
            return false
        }

        return true
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
}

