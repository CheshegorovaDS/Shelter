package com.example.shelter.presentation.extention

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.nextActivity(nav:  Class<*>, arg: Bundle? = null ) {
    var intent = Intent(this,nav)
    startActivity(intent)
}

fun AppCompatActivity.toast(text: String){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.toast(resId: Int){
    Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
}