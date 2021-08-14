package com.larissa.integrativeproject.presentation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.larissa.integrativeproject.data.model.AllMovies
import com.larissa.integrativeproject.databinding.AllMoviesItemBinding
import com.larissa.integrativeproject.presentation.ui.MovieDetailsActivity

class MoviesAdapter (val context: Context, val dataSet: MutableList<AllMovies> = mutableListOf()): RecyclerView.Adapter<MoviesAdapter.AllMoviesViewHolder>(){
    var favoriteChecked: (movie: AllMovies, isChecked: Boolean) -> Unit = { _, _ ->}

    inner class AllMoviesViewHolder(val binding: AllMoviesItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMoviesViewHolder {
        val binding = AllMoviesItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AllMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllMoviesViewHolder, position: Int) {
        with(holder) {
            with(dataSet[position]) {
                Glide.with(context)
                    .load(com.larissa.integrativeproject.data.model.Constants.BASE_POSTER_ALL_MOVIES_URL.value + dataSet[position].posterPath)
                    .into(binding.imgMoviePoster)

                binding.crdMovie.setOnClickListener {
                    val intent = Intent(context, MovieDetailsActivity::class.java)
                    intent.putExtra(MOVIE_ID, dataSet[position].movieId)
                    context.startActivity(intent)
                }
                binding.txtVoteAverage.text = allVoteAverageFormatted()
                binding.txtAllMovieTitle.text = title
                binding.BtnFavorite.setOnCheckedChangeListener(null)

                binding.BtnFavorite.isChecked = dataSet[position].isFavorite

                binding.BtnFavorite.setOnCheckedChangeListener { _, isChecked ->
                    favoriteChecked(dataSet[position], isChecked)
                }
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size

    companion object {
        const val MOVIE_ID = "movieId"
    }
}