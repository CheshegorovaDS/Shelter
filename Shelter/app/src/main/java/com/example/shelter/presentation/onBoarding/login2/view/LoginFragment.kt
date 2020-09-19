package com.example.shelter.presentation.onBoarding.login2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shelter.R
import com.example.shelter.presentation.onBoarding.login2.model.LoginDestination
import com.example.shelter.presentation.onBoarding.login2.model.LoginErrorCode
import io.reactivex.Observable

class LoginFragment: LoginView, Fragment() {
    private val resLayout = R.layout.activity_login

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

    }

    override fun clickLogin(): Observable<Pair<String, String>> {
        TODO("Not yet implemented")
    }

    override fun showError(code: LoginErrorCode) {
        TODO("Not yet implemented")
    }

    override fun navigateTo(loginDestination: LoginDestination) {
        TODO("Not yet implemented")
    }

    override fun initComponent() {
        TODO("Not yet implemented")
    }

    override fun navigateTo() {
        TODO("Not yet implemented")
    }
}