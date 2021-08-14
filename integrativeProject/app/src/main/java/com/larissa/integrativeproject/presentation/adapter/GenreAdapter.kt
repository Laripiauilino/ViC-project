package com.larissa.integrativeproject.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.larissa.integrativeproject.data.model.Genre
import com.larissa.integrativeproject.databinding.GenreItemBinding

class GenreAdapter (val context: Context, val dataSet: MutableList<Genre>): RecyclerView.Adapter<GenreAdapter.GenreViewHolder>(){
    var genreChecked : (genreIds: List<Int>) -> Unit = {}
    private val idGenresList: MutableList<Int> = mutableListOf()

    inner class GenreViewHolder(val binding: GenreItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {

        val binding = GenreItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {

        with(holder){
            with(dataSet[position]){
                binding.chpGenre.text = name

                binding.chpGenre.setOnCheckedChangeListener{_, isChecked ->
                    if (isChecked) {
                        idGenresList.add(dataSet[position].id)
                    } else {
                        idGenresList.remove(dataSet[position].id)
                    }
                    genreChecked(idGenresList)
                }
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size
}