package com.example.shelter.presentation.fragment_menu.news.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shelter.R
import com.example.shelter.app.ShelterManagerApp
import com.example.shelter.data.di.DaggerNewsRepositoryComponent
import com.example.shelter.presentation.about_animal.view.AboutAnimalActivity
import com.example.shelter.presentation.creating_news.view.CreatingNewsActivity
import com.example.shelter.presentation.filtering_news.view.FilteringNewsActivity
import com.example.shelter.presentation.fragment_menu.news.adapter.NewsAdapter
import com.example.shelter.presentation.fragment_menu.news.di.DaggerNewsComponent
import com.example.shelter.presentation.fragment_menu.news.model.FilterNews
import com.example.shelter.presentation.fragment_menu.news.model.NewsDestination
import com.example.shelter.presentation.fragment_menu.news.presenter.NewsPresenter
import com.example.shelter.presentation.log_in_app.view.LogInAppActivity
import com.example.shelter.presentation.model.News
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_news.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NewsFragment: Fragment(), NewsView {
    private val resLayout = R.layout.fragment_news

    @Inject
    lateinit var presenter: NewsPresenter

    private var adapter: NewsAdapter? = null
    private var checkedCategories = intArrayOf()
    private var checkedAnimalType = intArrayOf()

    override val updateNews: PublishSubject<FilterNews> = PublishSubject.create()
    override val clickOpenCard: PublishSubject<News> = PublishSubject.create()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(resLayout, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent()
    }

    override fun initComponent() {
        val appComponent = (activity?.application as ShelterManagerApp)
            .getAppComponent()

        val newsRepository = DaggerNewsRepositoryComponent.builder().build()

        DaggerNewsComponent.builder()
            .appComponent(appComponent)
            .newsRepositoryComponent(newsRepository)
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        updateNews.onNext(FilterNews())

        buttonSearch.setOnClickListener {
            updateNews.onNext(FilterNews(request = search.text.toString()))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun clickAddCard(): Observable<Any> = RxView.clicks(addNews)

    override fun clickFilter(): Observable<Any> = RxView.clicks(buttonFilter)

    override fun clickClose(): Observable<Any> = RxView.clicks(closeSearch)

    override fun changeSearch(): Observable<String> = RxTextView.textChanges(search)
        .map { value ->  value.toString()}
        .debounce(DEBOUNCE_VALUE, TimeUnit.MILLISECONDS)

    private fun clickOpenCard(news: News) = clickOpenCard.onNext(news)

    override fun clickSearch(): Observable<Any> = RxView.clicks(search)

    override fun showNews(list: List<News>) {
        if (adapter == null) {
            adapter = NewsAdapter(::clickOpenCard)
            listAnimal.layoutManager = LinearLayoutManager(context)
            listAnimal.adapter = adapter
        }
        adapter?.updateList(list)
        adapter?.notifyDataSetChanged()
    }

    override fun showProgressBar(visibility: Boolean) {
        progressBar.visibility = if (visibility) {
            View.VISIBLE
        } else {
            requireActivity().findViewById<EditText>(R.id.search).isFocusableInTouchMode = true
            View.GONE
        }
    }

    override fun showListNews(visibility: Boolean) {
        listAnimal.visibility = if (visibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showSearchButton(visibility: Boolean) {
        requireActivity().findViewById<ImageView>(R.id.searchNews).visibility = if (visibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showFilterButton(visibility: Boolean) {
        val visibility = if (visibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
        requireActivity().findViewById<ImageView>(R.id.filterNews).visibility = visibility
        requireActivity().findViewById<Button>(R.id.buttonFilter).visibility = visibility
    }

    override fun showCancelButton(visibility: Boolean) {
        val visibility = if (visibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
        requireActivity().findViewById<ImageView>(R.id.closeSearch).visibility = visibility
        requireActivity().findViewById<Button>(R.id.buttonSearch).visibility = visibility
    }

    override fun showEnterButton(visibility: Boolean) {
        requireActivity().findViewById<ImageView>(R.id.enter).visibility = if (visibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showRequest(isClear: Boolean) {
        if (isClear) {
            requireActivity().findViewById<EditText>(R.id.search).setText("")
        }
    }

    override fun addNewsEnabled(isEnabled: Boolean) {
        addNews.isEnabled = isEnabled
    }

    override fun navigateTo(destination: NewsDestination) =
        when (destination) {
            NewsDestination.LOGIN_OR_REGISTRATION_SCREEN -> showLogInAppDialog()
            NewsDestination.ADD_ANIMAL_CARD -> openCreatingNews()
            NewsDestination.FILTER_CARD -> openFilteringNews()
        }

    private fun showLogInAppDialog() =
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.not_log_in))
            .setNegativeButton(getString(R.string.cancel)){ dialog, _ -> dialog.cancel()}
            .setNeutralButton(getString(R.string.log_in_app)){ dialog, _ -> dialog.apply {
                openLogInAppScreen()
            } }.create()
            .show()

    private fun openLogInAppScreen() {
        val intent = Intent(view?.context, LogInAppActivity::class.java)
        startActivity(intent)
    }

    private fun openCreatingNews() {
        val intent = Intent(view?.context, CreatingNewsActivity::class.java)
        startActivityForResult(intent, RESULT_CODE_CREATING)
    }

    private fun openFilteringNews() {
        val intent = Intent(requireContext(), FilteringNewsActivity::class.java)
        intent.putExtra(FILTER_CATEGORY_KEY, checkedCategories)
        intent.putExtra(FILTER_ANIMAL_TYPE_KEY, checkedAnimalType)
        startActivityForResult(intent, RESULT_CODE_FILTERING)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (resultCode) {
            RESULT_CODE_FILTERING -> {
                val checkedCategoriesId = data?.getIntArrayExtra(FILTER_CATEGORY_KEY)
                val checkedTypesId = data?.getIntArrayExtra(FILTER_ANIMAL_TYPE_KEY)

                updateNews.onNext(
                    FilterNews(
                        checkedCategoriesId?.toList() ?: listOf(),
                        checkedTypesId?.toList() ?: listOf()
                    )
                )
            }
            RESULT_CODE_CREATING -> {
                updateNews.onNext(FilterNews())
                val builder = AlertDialog.Builder(requireContext())
                builder
                    .setTitle(R.string.addCardIsSuccessful)
                    .setPositiveButton(R.string.good){dialog, _ ->
                        dialog.cancel()
                    }
                builder.create()
                builder.show()
            }
        }
    }

    override fun openCard(idCard: Int) {
        val intentActivity = Intent(requireContext(), AboutAnimalActivity::class.java)
        intentActivity.putExtra(ANIMAL_KEY, idCard)
        startActivity(intentActivity)
    }

    companion object {
        const val ANIMAL_KEY = "idAnimal"
        const val FILTER_CATEGORY_KEY = "checkedCategories"
        const val FILTER_ANIMAL_TYPE_KEY = "checkedAnimalTypes"
        const val DEBOUNCE_VALUE = 500L
        const val RESULT_CODE_FILTERING = 1
        const val RESULT_CODE_CREATING = 2
    }
}
