package com.example.shelter.presentation.fragment_menu.homepage.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shelter.R
import com.example.shelter.app.ShelterManagerApp
import com.example.shelter.data.di.DaggerNewsRepositoryComponent
import com.example.shelter.data.di.DaggerUserRepositoryComponent
import com.example.shelter.presentation.edit_user.view.EditUserActivity
import com.example.shelter.presentation.fragment_menu.homepage.adapter.HomepageAdapter
import com.example.shelter.presentation.fragment_menu.homepage.di.DaggerHomepageComponent
import com.example.shelter.presentation.fragment_menu.homepage.presenter.HomepagePresenter
import com.example.shelter.presentation.fragment_menu.news.view.NewsFragment
import com.example.shelter.presentation.menu.MenuActivity
import com.example.shelter.presentation.model.News
import com.example.shelter.presentation.onBoarding.registration.model.UserType
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_homepage.*
import javax.inject.Inject


class HomepageFragment: Fragment(), HomepageView, Toolbar.OnMenuItemClickListener {
    private val resLayout: Int  = R.layout.fragment_homepage

    @Inject
    lateinit var presenter: HomepagePresenter

    override val downloadUser: PublishSubject<Unit> = PublishSubject.create()
    override val clickOpenCard: PublishSubject<News> = PublishSubject.create()
    override val clickLogout: PublishSubject<Unit> = PublishSubject.create()
    override val clickEditCard: PublishSubject<News> = PublishSubject.create()
    override val clickDeleteCard: PublishSubject<Int> = PublishSubject.create()

    private var adapter: HomepageAdapter? = null

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

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.inflateMenu(R.menu.menu_user)
        toolbar.setOnMenuItemClickListener(this)
        if (toolbar.menu is MenuBuilder) {
            val menuBuilder = toolbar.menu as MenuBuilder
            menuBuilder.setOptionalIconsVisible(true)
        }

        presenter.attachView(this)
        downloadUser.onNext(Unit)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showLogoutDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder
            .setTitle(R.string.logoutTitle)
            .setNeutralButton(R.string.cancel) {dialog, _ ->
                dialog.cancel()
            }
            .setPositiveButton(R.string.yes){dialog, _ ->
                dialog.cancel()
                clickLogout.onNext(Unit)
            }
        builder.create()
        builder.show()
    }

    override fun showProgressBar(isVisible: Boolean) {
        requireActivity().findViewById<ProgressBar>(R.id.progressBar).visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showCards(isVisible: Boolean) {
        listAnimal.visibility = if (isVisible) {
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
            adapter = HomepageAdapter(
                ::clickOpenCard,
                ::clickMenuEditCard,
                ::clickMenuDeleteCard
            )
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

    override fun openEditPage() {
        val intent = Intent(view?.context, EditUserActivity::class.java)
        startActivity(intent)
    }

    override fun exit() {
        val currentActivity = activity as MenuActivity
        val fragment = NewsFragment()
        currentActivity.loadFragment(fragment)
        currentActivity.selectIcon(fragment)
    }

    private fun clickOpenCard(news: News) = clickOpenCard.onNext(news)

    private fun clickMenuEditCard(news: News) = clickEditCard.onNext(news)

    private fun clickMenuDeleteCard(id: Int) {
        showDeleteCardDialog(id)
    }

    private fun showDeleteCardDialog(id: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder
            .setTitle(R.string.deleteCardTitle)
            .setNeutralButton(R.string.cancel) {dialog, _ ->
                dialog.cancel()
            }
            .setPositiveButton(R.string.yes){dialog, _ ->
                dialog.cancel()
                clickDeleteCard.onNext(id)
            }
        builder.create()
        builder.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.edit -> {
                openEditPage()
            }
            R.id.logout -> {
                showLogoutDialog()
            }
        }
        return true
    }
}
