package com.example.shelter.presentation.fragment_menu.messager.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shelter.R
import com.example.shelter.presentation.fragment_menu.messager.model.Messager

class MessagerAdapter(internal var list: List<Messager>) : RecyclerView.Adapter<MessagerAdapter.TableViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_message, parent, false)
        return TableViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.nameUser.setText(list[position].fullName)
        holder.lastMessage.setText(list[position].messageText)
        holder.txtTime.setText(list[position].messageTime)
        if (list[position].unreadMessage == 0) {
            holder.txtUnreadMessage.visibility = View.INVISIBLE
        } else if (list[position].unreadMessage >= 100) {
            holder.txtUnreadMessage.text = "99+"
        } else {
            holder.txtUnreadMessage.setText("${list[position].unreadMessage}")
        }
        holder.imgAvatarUser.setImageResource(R.drawable.ic_user)
    }

    inner class TableViewHolder @SuppressLint("WrongViewCast")
    constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var imgAvatarUser: ImageView
        internal var nameUser: TextView
        internal var lastMessage: TextView
        internal var txtTime: TextView
        internal var txtUnreadMessage: TextView

        init {
            this.imgAvatarUser = view.findViewById<View>(R.id.imgAvatarUser) as ImageView
            this.nameUser = view.findViewById<View>(R.id.name) as TextView
            this.lastMessage = view.findViewById<View>(R.id.lastMessage) as TextView
            this.txtTime = view.findViewById<View>(R.id.time) as TextView
            this.txtUnreadMessage = view.findViewById<View>(R.id.countUnreadMessage) as TextView
        }

    }
}