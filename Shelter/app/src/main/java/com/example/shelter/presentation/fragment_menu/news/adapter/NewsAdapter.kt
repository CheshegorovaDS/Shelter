package com.example.shelter.presentation.fragment_menu.news.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivities
import androidx.recyclerview.widget.RecyclerView
import com.example.shelter.R
import com.example.shelter.presentation.about_animal.view.AboutAnimalActivity
import com.example.shelter.presentation.model.News

class NewsAdapter(internal var list: List<News>) : RecyclerView.Adapter<NewsAdapter.TableViewHolder>() {

    var view:View? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_news, parent, false)
        return TableViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_news, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.name.setText(list[position].name)
        holder.sex.setText(list[position].sex)
        holder.age.setText(list[position].age.toString())
        holder.imgAnimal.setImageResource(R.drawable.ic_animal)

        holder.animalCard.setOnClickListener{ openCard()}
    }

    private fun openCard() {
        Toast.makeText(view?.context,"open card",Toast.LENGTH_SHORT).show()
        val intent = Intent(view?.context, AboutAnimalActivity::class.java)
        startActivities(view!!.context, arrayOf(intent))
    }

    inner class TableViewHolder @SuppressLint("WrongViewCast")
    constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var imgAnimal: ImageView
        internal var name: TextView
        internal var sex: TextView
        internal var age: TextView
        internal var animalCard: CardView

        init {
            this.imgAnimal = view.findViewById<View>(R.id.imgAnimal) as ImageView
            this.name = view.findViewById<View>(R.id.Name) as TextView
            this.sex = view.findViewById<View>(R.id.Sex) as TextView
            this.age = view.findViewById<View>(R.id.Age) as TextView
            this.animalCard = view.findViewById<CardView>(R.id.card_view_news)
        }

    }
}