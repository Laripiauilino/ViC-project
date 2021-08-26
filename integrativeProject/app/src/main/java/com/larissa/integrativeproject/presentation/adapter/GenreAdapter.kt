package com.larissa.integrativeproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.larissa.integrativeproject.data.model.Genre
import com.larissa.integrativeproject.databinding.GenreItemBinding
import com.larissa.integrativeproject.domain.CheckListener

class GenreAdapter (private val checkListener: CheckListener? = null,val dataSet: MutableList<Genre>): RecyclerView.Adapter<GenreAdapter.GenreViewHolder>(){
//    Modificar
     val genreIdsList: MutableList<Int> = mutableListOf()

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
                binding.chpGenre.setOnCheckedChangeListener(null)
                binding.chpGenre.isChecked = genreIdsList.contains(id)
                binding.chpGenre.setOnCheckedChangeListener{_, isChecked ->
                    if (isChecked) {
                        genreIdsList.add(id)
                    } else {
                        genreIdsList.remove(id)
                    }
                    checkListener?.onGenreCheckListener(genreIdsList)

               }
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size
}