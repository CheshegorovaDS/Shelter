package com.example.shelter.presentation.fragment_menu.homepage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shelter.R
import com.example.shelter.app.ShelterManagerApp
import com.example.shelter.data.di.DaggerNewsRepositoryComponent
import com.example.shelter.data.di.DaggerUserRepositoryComponent
import com.example.shelter.presentation.fragment_menu.homepage.di.DaggerHomepageComponent
import com.example.shelter.presentation.fragment_menu.homepage.presenter.HomepagePresenter
import com.example.shelter.presentation.fragment_menu.news.adapter.NewsAdapter
import com.example.shelter.presentation.model.News
import com.example.shelter.presentation.model.User
import com.example.shelter.presentation.onBoarding.registration.model.UserType
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_homepage.*
import kotlinx.android.synthetic.main.fragment_homepage.listAnimal
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class HomepageFragment: Fragment(), HomepageView {
    private val resLayout: Int  = R.layout.fragment_homepage

    @Inject
    lateinit var presenter: HomepagePresenter

    override val downloadUser: PublishSubject<Unit> = PublishSubject.create()
    override val clickOpenCard: PublishSubject<News> = PublishSubject.create()

    private var adapter: NewsAdapter? = null

    fun newInstance(): Fragment {
        return HomepageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  = inflater.inflate(resLayout, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent()
    }

    override fun initComponent() {
        val appComponent = (activity?.application as ShelterManagerApp)
            .getAppComponent()

        val newsRepository = DaggerNewsRepositoryComponent.builder().build()

        val userRepository = DaggerUserRepositoryComponent.builder().build()

        DaggerHomepageComponent.builder()
            .appComponent(appComponent)
            .newsRepositoryComponent(newsRepository)
            .userRepositoryComponent(userRepository)
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        downloadUser.onNext(Unit)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun clickLogout(): Observable<Any> {
        TODO("Not yet implemented")
    }

    override fun showProgressBar(isVisible: Boolean) {
        requireActivity().findViewById<ProgressBar>(R.id.progressBar).visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showCards(isVisible: Boolean) {
        cardNews.visibility = if (isVisible) {
            cardNewsTitle.visibility = View.VISIBLE
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showUserInfo(isVisible: Boolean) {
        val visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
        requireActivity().findViewById<CardView>(R.id.cardUser).visibility = visibility
        requireActivity().findViewById<CardView>(R.id.cardInformation).visibility = visibility
    }

    override fun showListIsEmpty(isVisible: Boolean) {
        txtNoNews.visibility = if (isVisible) {
            cardNewsTitle.visibility = View.VISIBLE
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun updateCards(list: List<News>) {
        if (adapter == null) {
            adapter = NewsAdapter(::clickOpenCard)
            listAnimal.layoutManager = LinearLayoutManager(context)
            listAnimal.adapter = adapter
        }
        adapter?.updateList(list)
        adapter?.notifyDataSetChanged()
    }

    override fun updateInfo(name: String, typeUser: UserType, email: String, phone: String) {
        txtFullName.text = name
        txtYouAre.text = when (typeUser) {
            UserType.HUMAN -> getString(R.string.human)
            UserType.ORGANIZATION -> getString(R.string.organisation)
        }
        requireActivity().findViewById<TextView>(R.id.email).text = email
        requireActivity().findViewById<TextView>(R.id.phone).text = phone
    }

    private fun clickOpenCard(news: News) = clickOpenCard.onNext(news)
}
