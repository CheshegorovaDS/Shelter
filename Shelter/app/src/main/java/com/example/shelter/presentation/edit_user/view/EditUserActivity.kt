package com.example.shelter.presentation.edit_user.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.shelter.R
import com.example.shelter.app.ShelterManagerApp
import com.example.shelter.data.di.DaggerUserRepositoryComponent
import com.example.shelter.presentation.edit_user.di.DaggerEditUserComponent
import com.example.shelter.presentation.edit_user.model.EditUserErrorCode
import com.example.shelter.presentation.edit_user.presenter.EditUserPresenter
import com.example.shelter.presentation.model.Human
import com.example.shelter.presentation.model.Organisation
import com.example.shelter.presentation.model.User
import com.example.shelter.presentation.onBoarding.registration.model.UserType
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_edit_user.*
import kotlinx.android.synthetic.main.activity_edit_user.toolbar
import javax.inject.Inject

class EditUserActivity: AppCompatActivity(), EditUserView {
    private val resLayout = R.layout.activity_edit_user

    @Inject
    lateinit var presenter: EditUserPresenter

    override val downloadInfo: PublishSubject<Unit> = PublishSubject.create()
    override val editUser: PublishSubject<User> = PublishSubject.create()

    private var type: UserType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resLayout)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initComponent()
        presenter.attachView(this)
        downloadInfo.onNext(Unit)

        findViewById<Button>(R.id.apply).setOnClickListener {
            editUser.onNext(getUser())
        }
    }

    override fun initComponent() {
        val appComponent = (application as ShelterManagerApp).getAppComponent()
        val userRepository = DaggerUserRepositoryComponent.builder().build()

        DaggerEditUserComponent.builder()
            .appComponent(appComponent)
            .userRepositoryComponent(userRepository)
            .build()
            .inject(this)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_back, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getUser(): User {
        var human: Human? = null
        var organisation: Organisation? = null
        when (type) {

            UserType.HUMAN -> {
                human = Human(
                    firstName = firstName.text.toString(),
                    lastName = lastName.text.toString(),
                    patronymic = patronymic.text.toString()
                )
            }

            UserType.ORGANIZATION -> {
                organisation = Organisation(title = organisationName.text.toString())
            }
        }
        return User(
            id = 0,
            email = email.text.toString(),
            phone = phone.text.toString(),
            city = city.text.toString(),
            country = country.text.toString(),
            human = human,
            organisation = organisation
        )
    }

    override fun showProgressBar(visibility: Boolean) {
        findViewById<ProgressBar>(R.id.progressBar).visibility = if (visibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showFields(typeUser: UserType) {
        this.type = typeUser
        when (typeUser) {
            UserType.HUMAN ->  {
                lastName.visibility = View.VISIBLE
                firstName.visibility = View.VISIBLE
                patronymic.visibility = View.VISIBLE
            }
            UserType.ORGANIZATION -> {
                organisationName.visibility = View.VISIBLE
            }
        }

        city.visibility = View.VISIBLE
        country.visibility = View.VISIBLE
        phone.visibility = View.VISIBLE
        email.visibility = View.VISIBLE
    }

    override fun showApply(visibility: Boolean) {
        findViewById<Button>(R.id.apply).visibility = if (visibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun enabledApply(isEnabled: Boolean) {
        findViewById<Button>(R.id.apply).isEnabled = isEnabled
    }

    override fun hideFields() {
        city.visibility = View.GONE
        country.visibility = View.GONE
        phone.visibility = View.GONE
        email.visibility = View.GONE
        lastName.visibility = View.GONE
        firstName.visibility = View.GONE
        patronymic.visibility = View.GONE
        organisationName.visibility = View.GONE
    }

    override fun showException(visibility: Boolean) {
        findViewById<TextView>(R.id.error).visibility = if (visibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun setHumanInfo(user: User) {
        setUserInfo(user)
        lastName.setText(user.human?.lastName)
        firstName.setText(user.human?.firstName)
        patronymic.setText(user.human?.patronymic ?: EMPTY_TEXT)
    }

    override fun setOrganisationInfo(user: User) {
        setUserInfo(user)
        organisationName.setText(user.organisation?.title)
    }

    private fun setUserInfo(user: User) {
        city.setText(user.city)
        country.setText(user.country)
        phone.setText(user.phone)
        email.setText(user.email)
    }

    override fun setException(code: EditUserErrorCode) {
        val text = when (code) {
            else -> R.id.error
        }
        findViewById<TextView>(R.id.error).setText(text)
    }

    override fun exit() {
        finish()
    }

    companion object {
        const val EMPTY_TEXT = ""
    }
}
