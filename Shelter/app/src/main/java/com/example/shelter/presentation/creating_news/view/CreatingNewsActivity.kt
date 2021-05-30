package com.example.shelter.presentation.creating_news.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.shelter.R
import com.example.shelter.presentation.about_animal.model.Sex
import com.example.shelter.presentation.about_animal.model.Sterilization
import com.example.shelter.presentation.creating_news.presenter.CreatingNewsPresenter
import com.example.shelter.presentation.creating_news.reducer.CreatingNewsReducer
import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.model.News
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_animal_card.*
import kotlinx.android.synthetic.main.activity_creating_news.*
import kotlinx.android.synthetic.main.activity_creating_news.toolbar

class CreatingNewsActivity: AppCompatActivity(), CreatingNewsView {
    private val resLayout = R.layout.activity_creating_news

    private var presenter: CreatingNewsPresenter = CreatingNewsPresenter(CreatingNewsReducer())

    override val tryCreateNews: PublishSubject<Animal> = PublishSubject.create()
    override val downloadParameters: PublishSubject<Unit> = PublishSubject.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resLayout)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

//        initComponent()
        presenter.attachView(this)
        downloadParameters.onNext(Unit)

        findViewById<Button>(R.id.createNews).setOnClickListener {
            tryCreateNews.onNext(getAnimal())
        }
    }

    override fun initComponent() {
        TODO("Not yet implemented")
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

    override fun showException(isVisible: Boolean) {
        val visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
        findViewById<TextView>(R.id.exception).visibility = visibility
    }

    override fun showProgressBar(isVisible: Boolean) {
        val visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
        findViewById<ProgressBar>(R.id.progressBar).visibility = visibility
    }

    private fun getAnimal(): Animal {
        return Animal(
            findViewById<TextView>(R.id.name).text.toString(),
            "",
            "",
            Sex.NONE,
            findViewById<TextView>(R.id.breed).text.toString(),
            0,
            "",
            findViewById<TextView>(R.id.description).text.toString()
        )
    }
}