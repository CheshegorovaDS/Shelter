package com.example.shelter.presentation.menu

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.shelter.R
import com.example.shelter.app.ShelterManagerApp
import com.example.shelter.presentation.log_in_app.view.LogInAppActivity
import com.example.shelter.presentation.extention.nextActivity
import com.example.shelter.presentation.fragment_menu.homepage.view.HomepageFragment
import com.example.shelter.presentation.fragment_menu.messager.view.MessagerFragment
import com.example.shelter.presentation.menu.di.DaggerMenuComponent
import com.example.shelter.presentation.fragment_menu.news.view.NewsFragment
import com.example.shelter.presentation.storage.LoggedUserProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_menu.*
import javax.inject.Inject


class MenuActivity: AppCompatActivity() {

    @Inject
    lateinit var loggedUserProvider: LoggedUserProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        initComponent()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadFragment(NewsFragment())
    }

    private fun initComponent() {
        val appComponent = (application as ShelterManagerApp).getAppComponent()
        DaggerMenuComponent
            .builder()
            .appComponent(appComponent)
            .build()
            .inject(this)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
        when(item.itemId){
            R.id.navigation_news -> {
                loadFragment(NewsFragment())
                return@OnNavigationItemSelectedListener true
            }
//            R.id.navigation_messager -> {
//                return@OnNavigationItemSelectedListener tryLoadFragment(
//                    MessagerFragment()
//                        .newInstance())
//            }
            R.id.navigation_homepage -> {
                return@OnNavigationItemSelectedListener tryLoadFragment(
                    HomepageFragment()
                        .newInstance())
            }
            else -> return@OnNavigationItemSelectedListener false
        }
    }

    fun loadFragment(fragment: Fragment)  = supportFragmentManager
        .beginTransaction()
        .replace(R.id.fl_content, fragment)
        .commit()


    private fun tryLoadFragment(fragment: Fragment): Boolean {
        if(checkUser()) {
            loadFragment(fragment)
        }else{
            AlertDialog.Builder(this)
                .setTitle("Вы не авторизованы")
                .setNegativeButton("Отмена"){dialog,id-> dialog.cancel()}
                .setNeutralButton("Войти в приложение"){dialog, _ -> dialog.apply { next() } }.create()
                .show()
            return false
        }
        return true
    }

    private fun checkUser():Boolean{
        val token = loggedUserProvider.getToken()
        return token != null
    }

    private fun next(){
        nextActivity(LogInAppActivity::class.java)
    }
}
