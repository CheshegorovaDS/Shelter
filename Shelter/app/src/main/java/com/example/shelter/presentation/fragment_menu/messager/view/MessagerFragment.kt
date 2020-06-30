package com.example.shelter.presentation.fragment_menu.messager.view

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shelter.presentation.base.AppFragment
import com.example.shelter.R
import com.example.shelter.presentation.fragment_menu.messager.adapter.MessagerAdapter
import com.example.shelter.presentation.fragment_menu.messager.presenter.MessagerPresenter
import kotlinx.android.synthetic.main.fragment_messager.*

class MessagerFragment: AppFragment(), MessagerView {
    override val resLayout: Int  = R.layout.fragment_messager
    lateinit var presenter: MessagerPresenter

    fun newInstance(): Fragment {
        return MessagerFragment()
    }

    override fun initView(view: View) {
        listMessage.layoutManager = LinearLayoutManager(context)
        presenter = MessagerPresenter(this)
        listMessage.setAdapter(MessagerAdapter(presenter.loadMesseges()))
        listMessage.addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
    }
}