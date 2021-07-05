package com.example.shelter.presentation.fragment_menu.homepage.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shelter.R
import com.example.shelter.presentation.fragment_menu.homepage.utils.DeleteCard
import com.example.shelter.presentation.fragment_menu.homepage.utils.EditCard
import com.example.shelter.presentation.fragment_menu.news.utils.OpenCardAnimal
import com.example.shelter.presentation.model.News

class HomepageAdapter (
    val clickOpenCardAnimal: OpenCardAnimal,
    val editCard: EditCard,
    val deleteCard: DeleteCard
): RecyclerView.Adapter<HomepageAdapter.TableViewHolder>() {

    private val list: MutableList<News> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder =
       TableViewHolder(
           LayoutInflater
               .from(parent.context)
               .inflate(R.layout.list_item_user_news, parent, false)
       )

    override fun getItemCount(): Int = list.size

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.toolbar.inflateMenu(R.menu.menu_card)

        if (holder.toolbar.menu is MenuBuilder) {
            val menuBuilder = holder.toolbar.menu as MenuBuilder
            menuBuilder.setOptionalIconsVisible(true)
        }
        holder.name.text = list[position].name
        holder.category.text = list[position].category
        if (list[position].photo != "") {
            Glide.with(holder.imgAnimal.context)
                .load(Uri.parse(list[position].photo)).into(holder.imgAnimal)
        } else {
            holder.imgAnimal.setImageResource(R.drawable.ic_pet_house)
        }

        holder.animalCard.setOnClickListener{ openCard(list[position])}

        holder.toolbar.setOnMenuItemClickListener { item ->
            when (item?.itemId) {
                R.id.edit -> editCard(list[position])
                R.id.delete -> deleteCard(list[position].id)
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun openCard(news: News) = clickOpenCardAnimal(news)

    fun updateList(list: List<News>) {
        this.list.clear()
        this.list.addAll(list)
    }

    inner class TableViewHolder @SuppressLint("WrongViewCast")
    constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var imgAnimal = view.findViewById<View>(R.id.imgAnimal) as ImageView
        internal var name = view.findViewById<View>(R.id.Name) as TextView
        internal var category = view.findViewById<View>(R.id.Category) as TextView
        internal var animalCard = view.findViewById<CardView>(R.id.card_view_news)
        internal var toolbar = view.findViewById<Toolbar>(R.id.toolbar)
    }
}
