package com.example.shelter.presentation.fragment_menu.homepage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shelter.R
import com.example.shelter.presentation.base.AppFragment
import com.example.shelter.presentation.fragment_menu.homepage.presenter.IHomepagePresenter

class HomepageFragment:AppFragment(),HomepageView {
    override val resLayout: Int  = R.layout.fragment_homepage
    lateinit var presenter:IHomepagePresenter

    fun newInstance(): Fragment {
        return HomepageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initView(view: View) {

    }
}