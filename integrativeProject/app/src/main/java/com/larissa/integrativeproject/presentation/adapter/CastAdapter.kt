package com.larissa.integrativeproject.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.larissa.integrativeproject.data.model.Cast
import com.larissa.integrativeproject.data.model.Constants
import com.larissa.integrativeproject.databinding.CastItemBinding

class CastAdapter  (val context: Context, val dataSet: MutableList<Cast>): RecyclerView.Adapter<CastAdapter.CastViewHolder>(){
    inner class CastViewHolder(val binding: CastItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {

        val binding = CastItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        with(holder){
            with(dataSet[position]){
                Glide.with(context)
                    .load(Constants.BASE_DETAILS_URL.value + dataSet[position].profilePath)
                    .into(binding.imgCharacter)
                binding.txtActorName.text = name
                binding.txtCharacterName.text = character
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size
}