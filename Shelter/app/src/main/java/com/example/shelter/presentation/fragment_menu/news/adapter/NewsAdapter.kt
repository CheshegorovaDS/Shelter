package com.example.shelter.presentation.fragment_menu.news.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shelter.R
import com.example.shelter.presentation.fragment_menu.news.utils.OpenCardAnimal
import com.example.shelter.presentation.model.News
import kotlinx.android.synthetic.main.activity_animal_card.*
import java.io.InputStream
import java.net.URL

class NewsAdapter (
    val clickOpenCardAnimal: OpenCardAnimal
): RecyclerView.Adapter<NewsAdapter.TableViewHolder>() {

    private val list: MutableList<News> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder  =
       TableViewHolder(
           LayoutInflater
               .from(parent.context)
               .inflate(R.layout.list_item_news, parent, false)
       )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.category.text = list[position].category

        if (list[position].photo != "") {
            if (list[position].photo.startsWith("h", false)) {
                Glide.with(holder.imgAnimal.context)
                    .load(Uri.parse(list[position].photo)).into(holder.imgAnimal)
            } else {
                holder.imgAnimal.setImageDrawable(
                    Drawable.createFromStream(URL(list[position].photo).content as InputStream?, "src"))
            }

        } else {
            holder.imgAnimal.setImageResource(R.drawable.ic_pet_house)
        }


        holder.animalCard.setOnClickListener{ openCard(list[position])}
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
    }
}
