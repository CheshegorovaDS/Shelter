package com.example.shelter.presentation.fragment_menu.news.view


import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivities
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shelter.R
import com.example.shelter.presentation.LogInAppActivity
import com.example.shelter.presentation.base.AppFragment
import com.example.shelter.presentation.fragment_menu.news.adapter.NewsAdapter
import com.example.shelter.presentation.fragment_menu.news.presenter.NewsPresenter
import com.example.shelter.presentation.storage.LoggedUserProvider
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject


class NewsFragment @Inject constructor(
    private val loggedUserProvider: LoggedUserProvider
) : AppFragment() {

    override val resLayout: Int = R.layout.fragment_news

    lateinit var presenter: NewsPresenter

    override fun initView(view: View) {
        listAnimal.layoutManager = LinearLayoutManager(context)
        presenter =
            NewsPresenter(
                this
            )
        listAnimal.adapter = NewsAdapter(
            presenter.loadNews()
        )
        listAnimal.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))


        search.setOnClickListener{
            Toast.makeText(context,"Поиск", Toast.LENGTH_SHORT).show()
        }

        filterNews.setOnClickListener{ Toast.makeText(context,"Фильтр", Toast.LENGTH_SHORT).show()}

//        addNews.setOnClickListener{
//            if(checkUser()){
//                Toast.makeText(context,"Добавить объявление", Toast.LENGTH_SHORT).show()
//            }else{
//                this.context?.let {
//                    AlertDialog.Builder(it)
//                        .setTitle("Вы не авторизованы")
//                        .setNegativeButton("Отмена"){dialog,id-> dialog.cancel()}
//                        .setNeutralButton("Войти в приложение"){dialog, id -> dialog.apply { next() } }.create()
//                        .show()
//                }
//            }
//        }

    }

    private fun checkUser():Boolean{
        val user = loggedUserProvider.getLoggedUser()
        return user != null
    }

    private fun next(){
        val intent = Intent(view?.context, LogInAppActivity::class.java)
        startActivities(requireView().context, arrayOf(intent))
    }
}
