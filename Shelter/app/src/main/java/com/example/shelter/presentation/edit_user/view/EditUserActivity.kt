package com.example.shelter.presentation.edit_user.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.shelter.R
import com.example.shelter.app.ShelterManagerApp
import com.example.shelter.data.di.DaggerUserRepositoryComponent
import com.example.shelter.presentation.edit_user.di.DaggerEditUserComponent
import com.example.shelter.presentation.edit_user.model.EditUserException
import com.example.shelter.presentation.edit_user.presenter.EditUserPresenter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resLayout)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initComponent()
        presenter.attachView(this)
        downloadInfo.onNext(Unit)
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

    override fun showFields(typeUser: UserType) {
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
    }

    override fun setHumanInfo(user: User) {
        lastName.setText(user.human?.lastName)
        firstName.setText(user.human?.firstName)
        patronymic.setText(user.human?.patronymic ?: EMPTY_TEXT)
    }

    override fun setOrganisationInfo(user: User) {
        organisationName.setText(user.organisation?.title)
    }

    override fun showException(exception: EditUserException) {
        TODO("Not yet implemented")
    }

    override fun exit() {
        TODO("Not yet implemented")
    }

    companion object {
        const val EMPTY_TEXT = ""
    }
}
