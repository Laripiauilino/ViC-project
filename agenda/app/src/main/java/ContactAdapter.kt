package com.larissa.agenda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter (val context: Context , var dataSet:MutableList<String>): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(), ContactListener{


    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val contact = view.findViewById<TextView>(R.id.txtDisplay)
    }
    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ContactViewHolder {
        val instantiateView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_second, parent, false)
        return ContactViewHolder(instantiateView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.contact.text = dataSet[position].displayContact()
    }

    override fun getItemCount(): Int = dataSet.size

    fun updateList(changedList: MutableList<String>){
        dataSet = changedList
        notifyDataSetChanged()
    }
    override fun onItemListener() {
        TODO("Not yet implemented")
    }
}