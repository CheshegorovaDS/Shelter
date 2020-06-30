package com.example.shelter.presentation.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment

interface INavigation {
    fun add(fragment: Fragment, bundle: Bundle)
    fun remove(fragment: Fragment)
    fun replace(fragment: Fragment, bundle: Bundle)
}