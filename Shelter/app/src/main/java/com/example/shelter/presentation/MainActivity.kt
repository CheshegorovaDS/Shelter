package com.example.shelter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.shelter.R
import com.example.shelter.presentation.extention.nextActivity
import com.example.shelter.presentation.onBoarding.login.view.LoginActivity
import kotlinx.android.synthetic.main.activity_login.*

import kotlinx.android.synthetic.main.activity_main.toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true);


        //if check all data
        btnLogIn.setOnClickListener { changeActivity() }

/*        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }

    fun changeActivity() {
        nextActivity(LoginActivity::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_back, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
