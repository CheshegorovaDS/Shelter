package com.example.shelter.presentation.fragment_menu.news.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.shelter.R
import com.example.shelter.presentation.fragment_menu.news.utils.OpenCardAnimal
import com.example.shelter.presentation.model.Animal

class NewsAdapter (
    val clickOpenCardAnimal: OpenCardAnimal
): RecyclerView.Adapter<NewsAdapter.TableViewHolder>() {

    private val list: MutableList<Animal> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder  =
       TableViewHolder(
           LayoutInflater
               .from(parent.context)
               .inflate(R.layout.list_item_news, parent, false)
       )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.sex.text = list[position].sex.toString()
        holder.age.text = list[position].age.toString()
        holder.imgAnimal.setImageResource(R.drawable.ic_animal)

        holder.animalCard.setOnClickListener{ openCard(list[position])}
    }

    private fun openCard(animal: Animal) = clickOpenCardAnimal(animal)

    fun updateList(list: List<Animal>) {
        this.list.clear()
        this.list.addAll(list)
    }

    inner class TableViewHolder @SuppressLint("WrongViewCast")
    constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var imgAnimal = view.findViewById<View>(R.id.imgAnimal) as ImageView
        internal var name = view.findViewById<View>(R.id.Name) as TextView
        internal var sex = view.findViewById<View>(R.id.Sex) as TextView
        internal var age = view.findViewById<View>(R.id.Age) as TextView
        internal var animalCard = view.findViewById<CardView>(R.id.card_view_news)
    }
}
