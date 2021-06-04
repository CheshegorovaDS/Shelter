package com.example.shelter.presentation.about_animal.view


import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.shelter.R
import com.example.shelter.app.ShelterManagerApp
import com.example.shelter.data.di.DaggerNewsRepositoryComponent
import com.example.shelter.data.di.DaggerUserRepositoryComponent
import com.example.shelter.presentation.about_animal.di.DaggerAboutAnimalComponent
import com.example.shelter.presentation.about_animal.presenter.AboutAnimalPresenter
import com.example.shelter.presentation.extention.toast
import com.example.shelter.presentation.fragment_menu.homepage.view.HomepageFragment
import com.example.shelter.presentation.fragment_menu.news.view.NewsFragment
import com.example.shelter.presentation.model.News
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_animal_card.*
import javax.inject.Inject


class AboutAnimalActivity: AppCompatActivity() , AboutAnimalView {
    private val resLayout = R.layout.activity_animal_card

    @Inject
    lateinit var presenter: AboutAnimalPresenter

    override val downloadNews: PublishSubject<Int> = PublishSubject.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resLayout)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initComponent()
        presenter.attachView(this)

        val idAnimal = intent.extras?.getInt(NewsFragment.ANIMAL_KEY)

        idAnimal?.let { downloadNews.onNext(it) }
    }

    override fun initComponent() {
        val appComponent = (application as ShelterManagerApp)
            .getAppComponent()

        val newsRepository = DaggerNewsRepositoryComponent.builder().build()
        val userRepository = DaggerUserRepositoryComponent.builder().build()

        DaggerAboutAnimalComponent.builder()
            .appComponent(appComponent)
            .newsRepositoryComponent(newsRepository)
            .userRepositoryComponent(userRepository)
            .build()
            .inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
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

    override fun clickUserCard(): Observable<Any> = RxView.clicks(cardUser)

    override fun updateNews(news: News) {
        Glide.with(imgAnimal.context)
            .load(Uri.parse(news.photo)).into(imgAnimal)
        findViewById<TextView>(R.id.name).text = news.name
        findViewById<TextView>(R.id.age).text = news.age.toString()
        findViewById<TextView>(R.id.sex).text = news.sex
        findViewById<TextView>(R.id.breed).text = news.breed
        findViewById<TextView>(R.id.passport).text = news.passport
        findViewById<TextView>(R.id.description).text = news.description
        var userType = getString(R.string.human)
        val name = if (news.user?.human == null) {
            userType = getString(R.string.organisation)
            news.user!!.organisation!!.title
        } else {
            "${news.user.human.firstName} ${news.user.human.lastName} " +
                    (news.user.human.patronymic ?: "")
        }
        updateUser(name ?: "no name", news.user.phone, userType)
    }

    private fun updateUser(name: String, phone: String, userType: String) {
        findViewById<TextView>(R.id.txtFullName).text = name
        findViewById<TextView>(R.id.txtConnection).text = phone
        findViewById<TextView>(R.id.txtUser).text = userType
    }

    override fun showAnimalInfo(name: String) {
        findViewById<TextView>(R.id.name).text = name
    }

    override fun showPhotoUser(visibility: Boolean) {
        if (visibility) {
            Glide.with(imgAvatarUser.context)
                .load(Uri.parse("avatarUser")).into(imgAvatarUser)
        }
    }

    override fun showBreed(visibility: Boolean) {
        if (visibility) {
            layoutBreed.visibility = View.VISIBLE
        } else {
            layoutBreed.visibility = View.GONE
        }
    }

    override fun showAge(visibility: Boolean) {
        if (visibility) {
            layoutAge.visibility = View.VISIBLE
        } else {
            layoutAge.visibility = View.GONE
        }
    }

    override fun showPassport(visibility: Boolean) {
        if (visibility) {
            layoutPassport.visibility = View.VISIBLE
        } else {
            layoutPassport.visibility = View.GONE
        }
    }

    override fun showSex(visibility: Boolean) {
        if (visibility) {
            layoutSex.visibility = View.VISIBLE
        } else {
            layoutSex.visibility = View.GONE
        }
    }

    override fun showUser(visibility: Boolean) {
        if (visibility) {
            cardUser.visibility = View.VISIBLE
        } else {
            cardUser.visibility = View.GONE
        }
    }

    override fun showDescription(visibility: Boolean) {
        if(visibility) {
            layoutDescription.visibility = View.VISIBLE
        } else {
            layoutDescription.visibility = View.GONE
        }
    }

    override fun showPhoto(visibility: Boolean) {
        if(visibility) {
            imgAnimal.visibility = View.VISIBLE
        } else {
            imgAnimal.visibility = View.GONE
        }
    }

    override fun showName(visibility: Boolean) {
        if(visibility) {
            layoutName.visibility = View.VISIBLE
        } else {
            layoutName.visibility = View.GONE
        }
    }

    override fun showCategory(visibility: Boolean) {

    }

    override fun showProgressBar(visibility: Boolean) {
        if(visibility) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun openUserHomepage(idUser: Int) {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fl_content, HomepageFragment())
//            .commit()
    }
}