package com.example.shelter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shelter.R
import kotlinx.android.synthetic.main.activity_main.*

class LogInAppActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in_app)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}