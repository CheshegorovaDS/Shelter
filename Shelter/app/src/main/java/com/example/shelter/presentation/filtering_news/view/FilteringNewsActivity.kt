package com.example.shelter.presentation.filtering_news.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shelter.R
import com.example.shelter.data.di.DaggerAnimalTypeRepositoryComponent
import com.example.shelter.data.di.DaggerCategoryRepositoryComponent
import com.example.shelter.presentation.extention.toast
import com.example.shelter.presentation.filtering_news.adapter.AnimalTypeFilterAdapter
import com.example.shelter.presentation.filtering_news.adapter.CategoryFilterAdapter
import com.example.shelter.presentation.filtering_news.di.DaggerFilteringNewsComponent
import com.example.shelter.presentation.filtering_news.presenter.FilteringNewsPresenter
import com.example.shelter.presentation.fragment_menu.news.view.NewsFragment
import com.example.shelter.presentation.model.AnimalType
import com.example.shelter.presentation.model.Category
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_animal_card.toolbar
import kotlinx.android.synthetic.main.activity_filtering_news.*
import javax.inject.Inject


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class FilteringNewsActivity: AppCompatActivity(), FilteringNewsView {
    private val resLayout = R.layout.activity_filtering_news

    @Inject
    lateinit var presenter: FilteringNewsPresenter

    private var categoryAdapter: CategoryFilterAdapter? = null
    private var animalTypeAdapter: AnimalTypeFilterAdapter? = null

    override val downloadData: PublishSubject<Unit> = PublishSubject.create()
    override val updateCheckedType: PublishSubject<List<AnimalType>> = PublishSubject.create()
    override val updateCheckedCategory: PublishSubject<List<Category>> = PublishSubject.create()
    override val clickApplyFilters: PublishSubject<Pair<List<Category>, List<AnimalType>>> = PublishSubject.create()

    private var checkedCategoriesId = intArrayOf()
    private var checkedTypesId = intArrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resLayout)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        checkedCategoriesId = intent.getIntArrayExtra(NewsFragment.FILTER_CATEGORY_KEY)
        checkedTypesId = intent.getIntArrayExtra(NewsFragment.FILTER_ANIMAL_TYPE_KEY)

        initComponent()
        presenter.attachView(this)
        downloadData.onNext(Unit)

        findViewById<Button>(R.id.apply).setOnClickListener {
//            clickApplyFilters.onNext(Pair(checkedCategories, checkedTypes))
            val intentResult = Intent()
            intentResult.putExtra(NewsFragment.FILTER_CATEGORY_KEY, checkedCategoriesId)
            intentResult.putExtra(NewsFragment.FILTER_ANIMAL_TYPE_KEY, checkedTypesId)
            setResult(1, intentResult)
            finish()
        }
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

    override fun clickChoosingCategory(): Observable<Any> = RxView.clicks(category)

    override fun clickChoosingAnimalTypes(): Observable<Any> = RxView.clicks(animalType)

    override fun showCategoriesDialog(list: List<Pair<Category, Boolean>>) {
        var array = emptyArray<String>()
        var checkedArray = booleanArrayOf()
        list.forEach { pair ->
            array += pair.first.title
            checkedArray += pair.second
        }

        val builder = createAlertDialog(
            resources.getString(R.string.choseCategory),
            array,
            checkedArray
        )
        builder.setPositiveButton(
            resources.getString(R.string.complete)
        ){ _, _ ->
            val categories = mutableListOf<Category>()
            checkedCategoriesId = IntArray(checkedArray.size)
                checkedArray.forEachIndexed { index, isChecked ->
                    if (isChecked) {
                        categories.add(list[index].first)
                        checkedCategoriesId[index] = list[index].first.id
                    }
                }
            updateCheckedCategory.onNext(categories)
            }
            .setMultiChoiceItems(
                array, checkedArray
            ) { _, index, isChecked ->
                checkedArray[index] = isChecked
            }

        builder.show()
    }

    override fun showAnimalTypesDialog(list: List<AnimalType>) {
        var array = emptyArray<String>()
        list.forEach { type ->
            array += type.title
        }

        val checkedArray = booleanArrayOf(false, false, false)

        val builder = createAlertDialog(
            resources.getString(R.string.choseAnimalType),
            array,
            checkedArray
        )
        builder.setPositiveButton(
            resources.getString(R.string.complete)
        ){ _, _ ->
            val types = mutableListOf<AnimalType>()
            checkedTypesId = IntArray(checkedArray.size)
            checkedArray.forEachIndexed { index, isChecked ->
                if (isChecked) {
                    types.add(list[index])
                    checkedTypesId[index] = list[index].id
                }
            }
            updateCheckedType.onNext(types)
        }
            .setMultiChoiceItems(
                array, checkedArray
            ) { _, index, isChecked ->
                checkedArray[index] = isChecked
            }

        builder.show()
    }

    override fun updateCheckedCategories(list: List<Category>) {
        if (categoryAdapter == null) {
            categoryAdapter = CategoryFilterAdapter(::clickCloseCategory)
            listCategories.layoutManager = LinearLayoutManager(this)
            listCategories.adapter = categoryAdapter
        }
        categoryAdapter?.updateList(list)
        categoryAdapter?.notifyDataSetChanged()
    }

    override fun updateCheckedTypes(list: List<AnimalType>) {
        if (animalTypeAdapter == null) {
            animalTypeAdapter = AnimalTypeFilterAdapter(::clickCloseType)
            listTypes.layoutManager = LinearLayoutManager(this)
            listTypes.adapter = animalTypeAdapter
        }
        animalTypeAdapter?.updateList(list)
        animalTypeAdapter?.notifyDataSetChanged()
    }

    private fun clickCloseType(list: List<AnimalType>) = updateCheckedType.onNext(list)

    private fun clickCloseCategory(list: List<Category>) = updateCheckedCategory.onNext(list)

    override fun applyFilters() {
        toast("apply")
    }

    private fun createAlertDialog(
        title: String,
        array: Array<String>,
        checkedArray: BooleanArray
    ): AlertDialog.Builder {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setCancelable(true)
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
        findViewById<EditText>(R.id.animalType).visibility = if (isVisible) {
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

    override fun listCategoryVisibility(isVisible: Boolean) {
        findViewById<RecyclerView>(R.id.listCategories).visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun listAnimalTypeVisibility(isVisible: Boolean) {
        findViewById<RecyclerView>(R.id.listTypes).visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

}

