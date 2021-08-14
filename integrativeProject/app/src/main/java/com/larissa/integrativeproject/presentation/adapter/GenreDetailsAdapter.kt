package com.larissa.integrativeproject.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.larissa.integrativeproject.data.model.Genre
import com.larissa.integrativeproject.databinding.GenreDetailsItemBinding

class GenreDetailsAdapter (val context: Context, val dataSet: MutableList<Genre> = mutableListOf()): RecyclerView.Adapter<GenreDetailsAdapter.GenreDetailsViewHolder>(){
    inner class GenreDetailsViewHolder(val binding: GenreDetailsItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreDetailsViewHolder {

        val binding = GenreDetailsItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreDetailsViewHolder, position: Int) {
        with(holder){
            with(dataSet[position]){
                binding.txtGenreDetails.text = name
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size
}