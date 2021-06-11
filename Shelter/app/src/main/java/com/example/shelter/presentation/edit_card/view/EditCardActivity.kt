package com.example.shelter.presentation.edit_card.view

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
import com.example.shelter.data.di.DaggerNewsRepositoryComponent
import com.example.shelter.data.di.DaggerUserRepositoryComponent
import com.example.shelter.presentation.edit_user.di.DaggerEditUserComponent
import com.example.shelter.presentation.edit_card.model.EditCardErrorCode
import com.example.shelter.presentation.edit_card.presenter.EditCardPresenter
import com.example.shelter.presentation.model.Human
import com.example.shelter.presentation.model.News
import com.example.shelter.presentation.model.Organisation
import com.example.shelter.presentation.model.User
import com.example.shelter.presentation.onBoarding.registration.model.UserType
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_edit_user.*
import kotlinx.android.synthetic.main.activity_edit_user.toolbar
import javax.inject.Inject

class EditCardActivity: AppCompatActivity(), EditCardView {
    private val resLayout = R.layout.activity_edit_user

    @Inject
    lateinit var presenter: EditCardPresenter

    override val downloadInfo: PublishSubject<Unit> = PublishSubject.create()
    override val editCard: PublishSubject<News> = PublishSubject.create()

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
//            editCard.onNext(getNews())
        }
    }

    override fun initComponent() {
        val appComponent = (application as ShelterManagerApp).getAppComponent()
        val newsRepository = DaggerNewsRepositoryComponent.builder().build()

//        DaggerEditUserComponent.builder()
//            .appComponent(appComponent)
//            .userRepositoryComponent(userRepository)
//            .build()
//            .inject(this)
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

//    private fun getNews(): News {
//        return
//        News(
//            id = 0,
//            email = email.text.toString(),
//            phone = phone.text.toString(),
//            city = city.text.toString(),
//            country = country.text.toString(),
//            human = human,
//            organisation = organisation
//        )
//    }

    override fun showProgressBar(visibility: Boolean) {
        findViewById<ProgressBar>(R.id.progressBar).visibility = if (visibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showFields(visibility: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showApply(visibility: Boolean) {
        findViewById<Button>(R.id.apply).visibility = if (visibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showException(visibility: Boolean) {
        findViewById<TextView>(R.id.error).visibility = if (visibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun setCarInfo(news: News) {
//        lastName.setText(user.human?.lastName)
    }

    override fun setException(code: EditCardErrorCode) {
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
