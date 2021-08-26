package com.larissa.integrativeproject.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.larissa.integrativeproject.R
import com.larissa.integrativeproject.data.model.Constants
import com.larissa.integrativeproject.data.model.Movies
import com.larissa.integrativeproject.databinding.MoviesItemBinding
import com.larissa.integrativeproject.domain.CheckListener
import com.larissa.integrativeproject.presentation.screen.MovieDetailsActivity


class MoviesAdapter (private val context: Context , private val checkListener: CheckListener? = null, var dataSet: MutableList<Movies>): RecyclerView.Adapter<MoviesAdapter.AllMoviesViewHolder>() {

    inner class AllMoviesViewHolder(val binding: MoviesItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMoviesViewHolder {
        val binding = MoviesItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AllMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllMoviesViewHolder, position: Int) {
        with(holder) {
            with(dataSet[position]) {
                Glide.with(context)
                    .load(Constants.BASE_POSTER_ALL_MOVIES_URL.value + dataSet[position].posterPath)
                    .into(binding.imgMoviePoster)

                binding.crdMovie.setOnClickListener {
                    val intent = Intent(context, MovieDetailsActivity::class.java)
                    intent.putExtra(MOVIE_ID, dataSet[position].movieId)
                    intent.putExtra(IS_FAVORITE, dataSet[position].isFavorite)
                    context.startActivity(intent)
                }
                binding.txtVoteAverage.text = allVoteAverageFormatted()
                binding.txtAllMovieTitle.text = title
                if (dataSet[position].isFavorite) {
                    binding.btnFavorite.setImageResource(R.drawable.ic_favorite_filled)
                }else{
                    binding.btnFavorite.setImageResource(R.drawable.ic_favorite_outlined)}
                binding.btnFavorite.setOnClickListener {
                        checkListener?.onFavoriteCheckListener(dataSet[position], true, position) }


                }
            }
        }


    override fun getItemCount(): Int = dataSet.size

    fun refreshFavorite(updatedFavoriteList: MutableList<Movies>) {
        dataSet = updatedFavoriteList
        notifyDataSetChanged()
    }

    companion object {
        const val MOVIE_ID = "movieId"
        const val IS_FAVORITE = "isFavorite"
    }
}


