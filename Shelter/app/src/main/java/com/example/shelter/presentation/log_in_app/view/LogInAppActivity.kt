package com.example.shelter.presentation.log_in_app.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.shelter.R
import com.example.shelter.presentation.extention.nextActivity
import com.example.shelter.presentation.log_in_app.model.LogInAppDestination
import com.example.shelter.presentation.log_in_app.presenter.ILogInAppPresenter
import com.example.shelter.presentation.log_in_app.presenter.LogInAppPresenter
import com.example.shelter.presentation.onBoarding.login.view.LoginActivity
import com.example.shelter.presentation.onBoarding.registration.view.RegistrationActivity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_log_or_reg.*
import kotlinx.android.synthetic.main.activity_main.toolbar

//class LogInAppActivity: AppCompatActivity(),LogInAppView {
//    lateinit var presenter: ILogInAppPresenter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_log_or_reg)
//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)
//
//        initView()
//
//        logIn.setOnClickListener { presenter.login()}
//        registration.setOnClickListener { presenter.registration() }
//    }
//
//    fun initView(){
//       // presenter = LogInAppPresenter(this)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_back, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId == android.R.id.home){
//            finish()
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    override val back: PublishSubject<Unit>
//        get() = TODO("Not yet implemented")
//
//    override fun clickLogin(): Observable<Any> {
//        TODO("Not yet implemented")
//    }
//
//    override fun clickRegistration(): Observable<Any> {
//        TODO("Not yet implemented")
//    }
//
//    override fun openLogin() {
//        nextActivity(LoginActivity::class.java)
//    }
//
//    override fun openRegistration() {
//        nextActivity(RegistrationActivity::class.java)
//    }
//
//    override fun navigationTo(destination: LogInAppDestination) {
//        TODO("Not yet implemented")
//    }
//
//
//}