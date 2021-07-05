package com.example.shelter.presentation.onBoarding.login.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.shelter.R
import com.example.shelter.app.ShelterManagerApp
import com.example.shelter.data.di.DaggerUserRepositoryComponent
import com.example.shelter.presentation.log_in_app.view.LogInAppActivity
import com.example.shelter.presentation.onBoarding.login.di.DaggerLoginComponent
import com.example.shelter.presentation.onBoarding.login.model.LoginDestination
import com.example.shelter.presentation.onBoarding.login.model.LoginErrorCode
import com.example.shelter.presentation.onBoarding.login.model.LoginException
import com.example.shelter.presentation.onBoarding.login.presenter.ILoginPresenter
import com.example.shelter.presentation.storage.LoggedUserProvider
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment: LoginView, Fragment() {
    private val resLayout = R.layout.fragment_login

    @Inject
    lateinit var presenter: ILoginPresenter
    @Inject
    lateinit var manager: LoggedUserProvider

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
        setHasOptionsMenu(true)


        presenter.attachView(this)

        logIn.setOnClickListener {
            clickLogin()
        }
    }

    override fun initComponent() {
        val appComponent = (activity?.application as ShelterManagerApp)
            .getAppComponent()

        val userComponent = DaggerUserRepositoryComponent.builder().build()

        DaggerLoginComponent.builder()
            .userRepositoryComponent(userComponent)
            .appComponent(appComponent)
            .build()
            .inject(this)
    }

    override fun clickLogin() {
        clickLogin.onNext(Pair(
            email.text.toString(),
            password.text.toString()
        ))
    }

    override fun showError(exception: LoginException) {
        val text = when (exception.id) {
            LoginErrorCode.INTERNET_CONNECTION_EXCEPTION -> "Ошибка соединения"
            LoginErrorCode.REQUEST_STATUS_ERROR -> "Неправильно введены данные"
            else -> "Ошибка соединения"
        }
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    override fun navigateTo(loginDestination: LoginDestination) {
        when (loginDestination) {
            LoginDestination.LOGIN_OR_REGISTRATION_SCREEN -> {
                val intent = Intent(context, LogInAppActivity::class.java)
                startActivity(intent)
            }
            LoginDestination.NEWS_SCREEN -> requireActivity().finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home ->  view?.findNavController()
                ?.navigate(R.id.action_loginFragment_to_baseFragment2)
        }
        return super.onOptionsItemSelected(item)
    }
}