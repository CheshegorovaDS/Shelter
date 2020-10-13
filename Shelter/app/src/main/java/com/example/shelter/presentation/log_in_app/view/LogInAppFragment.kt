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
import com.example.shelter.presentation.DEBOUNCE_VALUE
import com.example.shelter.presentation.log_in_app.di.DaggerLogInAppComponent
import com.example.shelter.presentation.log_in_app.model.LogInAppDestination
import com.example.shelter.presentation.log_in_app.presenter.ILogInAppPresenter
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_log_or_reg.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class LogInAppFragment :
    Fragment(),
    LogInAppView,
    Toolbar.OnMenuItemClickListener {

    private val resLayout = R.layout.activity_log_or_reg

    @Inject
    lateinit var presenter: ILogInAppPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initComponent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(resLayout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override val back: PublishSubject<Unit> = PublishSubject.create()

    override fun clickLogin(): Observable<Any> = RxView.clicks(logIn)

    override fun clickRegistration(): Observable<Any> = RxView.clicks(registration)

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

    override fun loginEnabled(isEnable: Boolean) {
        logIn.isEnabled = isEnable
    }

    override fun registrationEnabled(isEnable: Boolean) {
        registration.isEnabled = isEnable
    }

    override fun initComponent() {
        DaggerLogInAppComponent
            .builder().build()
            .inject(this)
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
