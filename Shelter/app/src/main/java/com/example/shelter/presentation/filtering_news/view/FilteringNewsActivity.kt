package com.example.shelter.presentation.filtering_news.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.shelter.R
import com.example.shelter.data.di.DaggerAnimalTypeRepositoryComponent
import com.example.shelter.data.di.DaggerCategoryRepositoryComponent
import com.example.shelter.presentation.extention.toast
import com.example.shelter.presentation.filtering_news.di.DaggerFilteringNewsComponent
import com.example.shelter.presentation.filtering_news.presenter.FilteringNewsPresenter
import com.example.shelter.presentation.model.AnimalType
import com.example.shelter.presentation.model.Category
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_animal_card.toolbar
import kotlinx.android.synthetic.main.activity_filtering_news.*
import javax.inject.Inject


class FilteringNewsActivity: AppCompatActivity(), FilteringNewsView {
    private val resLayout = R.layout.activity_filtering_news

    @Inject
    lateinit var presenter: FilteringNewsPresenter

    override val downloadData: PublishSubject<Unit> = PublishSubject.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resLayout)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initComponent()
        presenter.attachView(this)
        downloadData.onNext(Unit)

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

    override fun initComponent() {
        val categoryRepository = DaggerCategoryRepositoryComponent.builder().build()
        val animalTypeRepository = DaggerAnimalTypeRepositoryComponent.builder().build()

        DaggerFilteringNewsComponent.builder()
            .categoryRepositoryComponent(categoryRepository)
            .animalTypeRepositoryComponent(animalTypeRepository)
            .build()
            .inject(this)
    }

    override fun clickApply(): Observable<Any> = RxView.clicks(apply)

    override fun clickChoosingCategory(): Observable<Any> = RxView.clicks(category)

    override fun clickChoosingAnimalTypes(): Observable<Any> = RxView.clicks(type)

    override fun updateCategories(list: List<Category>) {
        var array = emptyArray<String>()
        list.forEach { category ->
            array += category.title
        }

        createAlertDialog(
            resources.getString(R.string.choseCategory),
            array
        ).show()
    }

    override fun updateAnimalTypes(list: List<AnimalType>) {
        var array = emptyArray<String>()
        list.forEach { type ->
            array += type.title
        }

        createAlertDialog(
            resources.getString(R.string.choseCategory),
            array
        ).show()
    }

    override fun applyFilters() {
        toast("apply")
    }

    private fun createAlertDialog(title: String, array: Array<String>): AlertDialog.Builder {
        val checkedArray = booleanArrayOf(false, false, false)
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setCancelable(true)
        builder.setMultiChoiceItems(array, checkedArray
        ) { _, index, isChecked ->
            checkedArray[index] = isChecked
        }
        builder
            .setPositiveButton(
                resources.getString(R.string.complete)
            ){ _, _ -> }
            .setNegativeButton(
                resources.getString(R.string.cancel)
            ){ dialog, _ -> dialog.cancel() }
        return builder
    }

    override fun progressBarVisibility(isVisible: Boolean) {
        findViewById<ProgressBar>(R.id.progressBar).visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun categoriesVisibility(isVisible: Boolean) {
        findViewById<EditText>(R.id.category).visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun animalTypesVisibility(isVisible: Boolean) {
        findViewById<EditText>(R.id.type).visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun applyVisibility(isVisible: Boolean) {
        findViewById<Button>(R.id.apply).visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }


}

