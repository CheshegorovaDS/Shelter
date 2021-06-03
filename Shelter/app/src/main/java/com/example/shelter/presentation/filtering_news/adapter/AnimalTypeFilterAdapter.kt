package com.example.shelter.presentation.filtering_news.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shelter.R
import com.example.shelter.presentation.filtering_news.utils.clickCloseType
import com.example.shelter.presentation.model.AnimalType

class AnimalTypeFilterAdapter constructor(
    val clickCloseType: clickCloseType
): RecyclerView.Adapter<AnimalTypeFilterAdapter.TableViewHolder>() {

    private val list: MutableList<AnimalType> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalTypeFilterAdapter.TableViewHolder =
        TableViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item_filter, parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.title.text = list[position].title

        holder.delete.setOnClickListener {
            list.removeAt(position)
            clickCloseType(list.toList())
        }
    }

    fun updateList(list: List<AnimalType>) {
        this.list.clear()
        this.list.addAll(list)
    }

    inner class TableViewHolder @SuppressLint("WrongViewCast")
    constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var title = view.findViewById<TextView>(R.id.filter)
        internal var delete = view.findViewById<Button>(R.id.closeFilter)
    }
}