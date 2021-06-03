package com.example.shelter.presentation.filtering_news.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shelter.R
import com.example.shelter.presentation.filtering_news.utils.clickCloseCategory
import com.example.shelter.presentation.model.Category

class CategoryFilterAdapter constructor(
    val clickCloseCategory: clickCloseCategory
): RecyclerView.Adapter<CategoryFilterAdapter.TableViewHolder>() {

    private val list: MutableList<Category> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryFilterAdapter.TableViewHolder =
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
            clickCloseCategory(list.toList())
        }
    }

    fun updateList(list: List<Category>) {
        this.list.clear()
        this.list.addAll(list)
    }

    inner class TableViewHolder @SuppressLint("WrongViewCast")
    constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var title = view.findViewById<TextView>(R.id.filter)
        internal var delete = view.findViewById<Button>(R.id.closeFilter)
    }
}
