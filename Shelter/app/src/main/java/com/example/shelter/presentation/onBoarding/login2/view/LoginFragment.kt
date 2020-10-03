package com.example.shelter.presentation.onBoarding.login2.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shelter.R
import com.example.shelter.app.ShelterManagerApp
import com.example.shelter.presentation.onBoarding.login2.model.LoginDestination
import com.example.shelter.presentation.onBoarding.login2.model.LoginErrorCode
import com.example.shelter.presentation.onBoarding.login2.presenter.ILoginPresenter
import com.example.shelter.presentation.onBoarding.login2.presenter.LoginPresenter
import com.example.shelter.presentation.onBoarding.registration.view.RegistrationActivity
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginFragment: LoginView, Fragment() {
    private val resLayout = R.layout.activity_login

    //@Inject
    lateinit var presenter: ILoginPresenter

    override val clickLogin: PublishSubject<Pair<String, String>> = PublishSubject.create()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(resLayout, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)

        logIn.setOnClickListener {
            clickLogin()
        }
    }

    override fun initComponent() {
        presenter = LoginPresenter()
//        val appComponent = (activity?.application as ShelterManagerApp)
//            .getAppComponent()


    }

    override fun clickLogin() {
        clickLogin.onNext(Pair(
            email.text.toString(),
            password.text.toString()
        ))
    }

    override fun showError(code: LoginErrorCode) {
        val text = when (code) {
            LoginErrorCode() -> "error"
            else -> ""
        }
        Toast.makeText(context, "", Toast.LENGTH_LONG).show()
    }

    override fun navigateTo(loginDestination: LoginDestination) {
        when (loginDestination) {
            LoginDestination.LOGIN_OR_REGISTRATION_SCREEN -> {
                val intent = Intent(context, RegistrationActivity::class.java)
                startActivity(intent)
            }
        }
    }
}