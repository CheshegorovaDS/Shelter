package com.example.shelter.presentation.news.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shelter.R
import com.example.shelter.app.ShelterManagerApp
import com.example.shelter.presentation.LogInAppActivity
import com.example.shelter.presentation.about_animal.view.AboutAnimalActivity
import com.example.shelter.presentation.base.inrefaces.BaseView
import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.news.adapter.NewsAdapter
import com.example.shelter.presentation.news.di.DaggerNewsComponent
import com.example.shelter.presentation.news.model.NewsDestination
import com.example.shelter.presentation.news.presenter.NewsPresenter
import com.example.shelter.presentation.storage.LoggedUserProvider
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class NewsFragment: Fragment(), NewsView, BaseView {
    private val resLayout = R.layout.fragment_news

    @Inject
    lateinit var presenter: NewsPresenter
    @Inject
    lateinit var manager: LoggedUserProvider

    private var adapter: NewsAdapter? = null

    override val updateNews: PublishSubject<Unit> = PublishSubject.create()
    override val clickOpenCard: PublishSubject<Animal> = PublishSubject.create()

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

        DaggerNewsComponent.builder()
            .appComponent(appComponent)
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        updateNews.onNext(Unit)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun clickAddCard(): Observable<Any> = RxView.clicks(addNews)

    override fun clickSearch(): Observable<Any> {
        TODO("Not yet implemented")
    }

    override fun clickFilter(): Observable<Any> = RxView.clicks(filterNews)

    override fun showNews(list: List<Animal>) {
        if (adapter == null) {
            adapter = NewsAdapter(::clickOpenCard)
            listAnimal.layoutManager = LinearLayoutManager(context)
            listAnimal.adapter = adapter
            listAnimal.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
        adapter?.updateList(list as MutableList<Animal>)
        adapter?.notifyDataSetChanged()
    }

    private fun clickOpenCard(animal: Animal) = clickOpenCard.onNext(animal)

    override fun openCard(intentAnimal: Intent) {
        val intentActivity = Intent(requireContext(), AboutAnimalActivity::class.java)
        intentActivity.putExtra("animal", intentAnimal)
        startActivity(intentActivity)
    }

    override fun navigateTo(destination: NewsDestination) =
        when (destination) {
            NewsDestination.LOGIN_OR_REGISTRATION_SCREEN -> showLogInAppDialog()
            NewsDestination.ADD_ANIMAL_CARD -> openCreatingNews()
        }

    override fun openFilter() {
        Toast.makeText(context, "open filter", Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar(visible: Boolean) {
        progressBar.visibility = if (visible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun addNewsEnabled(isEnabled: Boolean) {
        addNews.isEnabled = isEnabled
    }

    private fun showLogInAppDialog() =
        AlertDialog.Builder(requireContext())
            .setTitle("Вы не авторизованы")
            .setNegativeButton("Отмена"){dialog, _ -> dialog.cancel()}
            .setNeutralButton("Войти в приложение"){dialog, _ -> dialog.apply {
                openLogInAppScreen()
            } }.create()
            .show()

    private fun openLogInAppScreen() {
        val intent = Intent(view?.context, LogInAppActivity::class.java)
        startActivity(intent)
    }

    private fun openCreatingNews() {
        Toast.makeText(context, "create news", Toast.LENGTH_SHORT).show()
//        val intent = Intent(view?.context, LogInAppActivity::class.java)
//        startActivity(intent)
    }
}
