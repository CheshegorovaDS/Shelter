package com.example.shelter.presentation.log_in_app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.shelter.R
import com.example.shelter.presentation.log_in_app.model.LogInAppDestination
import com.example.shelter.presentation.log_in_app.presenter.ILogInAppPresenter
import com.example.shelter.presentation.log_in_app.presenter.LogInAppPresenter
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_log_or_reg.*

class LogInAppFragment: Fragment(), LogInAppView,  Toolbar.OnMenuItemClickListener {
    private val resLayout = R.layout.activity_log_or_reg

    //@Inject
    private lateinit var presenter: ILogInAppPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(resLayout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = LogInAppPresenter(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override val back: PublishSubject<Unit> = PublishSubject.create()

    override fun clickLogin(): Observable<Any> = RxView.clicks(logIn)

    override fun clickRegistration(): Observable<Any> = RxView.clicks(registration)

    override fun openLogin() {
        TODO("Not yet implemented")
    }

    override fun openRegistration() {
        TODO("Not yet implemented")
    }

    override fun navigationTo(destination: LogInAppDestination) {
        val s = when (destination) {
            LogInAppDestination.LOGIN_SCREEN -> R.id.action_baseFragment2_to_loginFragment
            LogInAppDestination.REGISTRATION_SCREEN -> R.id.action_baseFragment2_to_registrationActivity
            else -> null
        }

        s?.let {
            view?.findNavController()?.navigate(it)
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.home -> {
                back.onNext(Unit)
                true
            }
            else -> false
        }
    }

}