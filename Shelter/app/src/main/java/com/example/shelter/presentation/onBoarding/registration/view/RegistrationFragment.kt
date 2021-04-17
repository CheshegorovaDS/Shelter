package com.example.shelter.presentation.onBoarding.registration.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.shelter.R
import com.example.shelter.app.ShelterManagerApp
import com.example.shelter.presentation.log_in_app.view.LogInAppActivity
import com.example.shelter.presentation.onBoarding.registration.di.DaggerRegistrationComponent
import com.example.shelter.presentation.onBoarding.registration.model.*
import com.example.shelter.presentation.onBoarding.registration.presenter.IRegistrationPresenter
import com.example.shelter.presentation.storage.LoggedUserProvider
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_registration.*
import javax.inject.Inject

class RegistrationFragment: Fragment(), RegistrationView {

    private val resLayout = R.layout.fragment_registration

    @Inject
    lateinit var presenter: IRegistrationPresenter

    @Inject
    lateinit var manager: LoggedUserProvider

    override val clickRegistration: PublishSubject<User> = PublishSubject.create()
    override val updateUserType: PublishSubject<UserType> = PublishSubject.create()

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

        registration.setOnClickListener {
            clickRegistration()
        }

        editTxtYouAre.setOnClickListener {
            showListTypeUser()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun clickRegistration() {
        val user = when (editTxtYouAre.text.toString()) {
            resources.getString(R.string.human) -> {
                Human(
                    firstName = firstName.text.toString(),
                    lastName = lastName.text.toString(),
                    city = city.text.toString(),
                    phone = phone.text.toString(),
                    email = email.text.toString(),
                    password = password.text.toString()
                )
            }

            resources.getString(R.string.organisation) -> {
                Organisation(
                    name = organisationName.text.toString(),
                    city = city.text.toString(),
                    phone = phone.text.toString(),
                    email = email.text.toString(),
                    password = password.text.toString()
                )
            }

            else -> User()
        }

        clickRegistration.onNext(user)
    }

    override fun clickYouAre() = RxView.clicks(editTxtYouAre)

    override fun showListTypeUser(){
        val list = resources.getStringArray(R.array.typeUser)

        val builder = context?.let { AlertDialog.Builder(it) }

        var user = ""

        builder?.setTitle(resources.getString(R.string.you_are))
            ?.setCancelable(true)
            ?.setNegativeButton(resources.getString(R.string.back)) {
                    dialog, _ -> dialog.cancel()
            }

        builder?.setSingleChoiceItems(list, -1) {
                _, item -> user = list[item]
        }

        builder?.setPositiveButton("OK") { _, _ ->
            val type = if(user == resources.getString(R.string.human)){
                UserType.HUMAN
            } else {
                UserType.ORGANIZATION
            }
            updateUserType.onNext(type)
        }


        val alertDialog = builder?.create()
        alertDialog?.show()
    }

    override fun setUserType(type: UserType?) {
        if (type == null) {
            return
        }
        val userType = when (type) {
            UserType.HUMAN -> resources.getString(R.string.human)
            UserType.ORGANIZATION -> resources.getString(R.string.organisation)
        }
        editTxtYouAre.setText(userType)
    }

    override fun setFirstNameVisible(isVisible: Boolean) {
        firstName.visibility = when (isVisible) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    override fun setLastNameVisible(isVisible: Boolean) {
        lastName.visibility = when (isVisible) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    override fun setOrganisationNameVisible(isVisible: Boolean) {
        organisationName.visibility = when (isVisible) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    override fun showError(code: RegistrationException) {
        val text = when (code.id) {
            RegistrationErrorCode.INTERNET_CONNECTION_EXCEPTION -> "internet"

            RegistrationErrorCode.WRONG_ENTERED_DATA ->
                resources.getString(R.string.wrong_entered_data)

            else -> "error data input"
        }
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    override fun navigateTo(registerDestination: RegistrationDestination) {
        when (registerDestination) {
            RegistrationDestination.LOGIN_OR_REGISTRATION_SCREEN -> {
                val intent = Intent(context, LogInAppActivity::class.java)
                startActivity(intent)
            }

            RegistrationDestination.NEWS_SCREEN -> requireActivity().finish()

            else -> requireActivity().finish()
        }
    }

    override fun initComponent() {
        val appComponent = (activity?.application as ShelterManagerApp)
            .getAppComponent()

        DaggerRegistrationComponent.builder()
//            .userRepositoryComponent(repository)
            .appComponent(appComponent)
            .build()
            .inject(this)
    }

}