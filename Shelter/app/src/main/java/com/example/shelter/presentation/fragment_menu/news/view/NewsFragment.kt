package com.example.shelter.presentation.fragment_menu.news.view


import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivities
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shelter.R
import com.example.shelter.presentation.APP_LOGIN
import com.example.shelter.presentation.LogInAppActivity
import com.example.shelter.presentation.base.AppFragment
import com.example.shelter.presentation.fragment_menu.news.adapter.NewsAdapter
import com.example.shelter.presentation.fragment_menu.news.presenter.NewsPresenter
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : AppFragment {
    lateinit var presenter: NewsPresenter
    var sharedPreferences: SharedPreferences

    override val resLayout: Int = R.layout.fragment_news

//    fun newInstance(): Fragment{
//        return NewsFragment()
//    }

    constructor(sharedPreferences: SharedPreferences){
        this.sharedPreferences = sharedPreferences
    }

    override fun initView(view: View) {
        listAnimal.layoutManager = LinearLayoutManager(context)
        presenter =
            NewsPresenter(
                this
            )
        listAnimal.setAdapter(
            NewsAdapter(
                presenter.loadNews()
            )
        )
        listAnimal.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))


        search.setOnClickListener{view ->
            Toast.makeText(context,"Поиск", Toast.LENGTH_SHORT).show()
        }

        imgFilter.setOnClickListener{view -> Toast.makeText(context,"Фильтр", Toast.LENGTH_SHORT).show()}

        imgAdd.setOnClickListener{view ->
            if(checkUser()){
                Toast.makeText(context,"Добавить объявление", Toast.LENGTH_SHORT).show()
            }else{
                this.context?.let {
                    AlertDialog.Builder(it)
                        .setTitle("Вы не авторизованы")
                        .setNegativeButton("Отмена"){dialog,id-> dialog.cancel()}
                        .setNeutralButton("Войти в приложение"){dialog, id -> dialog.apply { next() } }.create()
                        .show()
                }
            }
        }

    }

    private fun checkUser():Boolean{
        //userManager = LoggedUserManager(mSettings)
        //val loggedUser: User? = userManager.getLoggedUser()
//        if(loggedUser == null){
//            return false
//        }
        var str: String? = sharedPreferences.getString(APP_LOGIN,"false")
        if(sharedPreferences.getString(APP_LOGIN,"").equals("true")){
            return true
        }
        return false
    }

    private fun next(){
        val intent = Intent(view?.context, LogInAppActivity::class.java)
        startActivities(requireView().context, arrayOf(intent))
    }


}