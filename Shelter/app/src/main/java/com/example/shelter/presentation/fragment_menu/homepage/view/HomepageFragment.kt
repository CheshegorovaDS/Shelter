package com.example.shelter.presentation.fragment_menu.homepage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shelter.R
import com.example.shelter.app.ShelterManagerApp
import com.example.shelter.data.di.DaggerNewsRepositoryComponent
import com.example.shelter.data.di.DaggerUserRepositoryComponent
import com.example.shelter.presentation.fragment_menu.homepage.di.DaggerHomepageComponent
import com.example.shelter.presentation.fragment_menu.homepage.presenter.HomepagePresenter
import com.example.shelter.presentation.fragment_menu.news.adapter.NewsAdapter
import com.example.shelter.presentation.model.News
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class HomepageFragment: Fragment(), HomepageView {
    private val resLayout: Int  = R.layout.fragment_homepage

    @Inject
    lateinit var presenter: HomepagePresenter

    override val downloadUser: PublishSubject<Unit> = PublishSubject.create()

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
        TODO("Not yet implemented")
    }

    override fun showCards(list: List<News>) {
        TODO("Not yet implemented")
    }
}