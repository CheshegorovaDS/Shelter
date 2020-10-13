package com.example.shelter.presentation.menu

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.shelter.R
import com.example.shelter.app.ShelterManagerApp
import com.example.shelter.presentation.APP_LOGIN
import com.example.shelter.presentation.APP_PREFERENCES
import com.example.shelter.presentation.LogInAppActivity
import com.example.shelter.presentation.extention.nextActivity
import com.example.shelter.presentation.fragment_menu.homepage.view.HomepageFragment
import com.example.shelter.presentation.fragment_menu.messager.view.MessagerFragment
import com.example.shelter.presentation.fragment_menu.news.view.NewsFragment
import com.example.shelter.presentation.menu.di.DaggerMenuComponent
import com.example.shelter.presentation.storage.LoggedUserProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_menu.*
import javax.inject.Inject


class MenuActivity: AppCompatActivity() {

    lateinit var mSettings: SharedPreferences
    @Inject
    lateinit var loggedUserProvider: LoggedUserProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        initComponent()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        mSettings = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
        loadFragment(
            NewsFragment(
                mSettings
            )
        )
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
                loadFragment(
                    NewsFragment(
                        mSettings
                    )
                )
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_messager -> {
                return@OnNavigationItemSelectedListener tryLoadFragment(
                    MessagerFragment()
                        .newInstance())
            }
            R.id.navigation_homepage -> {
                return@OnNavigationItemSelectedListener tryLoadFragment(
                    HomepageFragment()
                        .newInstance())
            }
            else -> return@OnNavigationItemSelectedListener false;
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
                .replace(R.id.fl_content, fragment)
            ft.commit()
    }

    private fun tryLoadFragment(fragment: Fragment): Boolean {
        if(checkUser()) {
            loadFragment(fragment)
        }else{
            AlertDialog.Builder(this)
                .setTitle("Вы не авторизованы")
                .setNegativeButton("Отмена"){dialog,id-> dialog.cancel()}
                .setNeutralButton("Войти в приложение"){dialog, id -> dialog.apply { next() } }.create()
                .show()
            return false
        }
        return true
    }

    private fun checkUser():Boolean{
        val user = loggedUserProvider.getLoggedUser()
        var str: String? = mSettings.getString(APP_LOGIN,"false")
        if(mSettings.getString(APP_LOGIN,"").equals("true") || user != null){
            return true
        }
        return false
    }

    private fun next(){
        nextActivity(LogInAppActivity::class.java)
    }

}