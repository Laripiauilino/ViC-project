package com.larissa.integrativeproject.presentation.screen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.larissa.integrativeproject.data.model.Movies
import com.larissa.integrativeproject.databinding.FragmentMoviesBinding
import com.larissa.integrativeproject.domain.CheckListener
import com.larissa.integrativeproject.domain.GenericErrorListener
import com.larissa.integrativeproject.presentation.adapter.GenreAdapter
import com.larissa.integrativeproject.presentation.adapter.MoviesAdapter
import com.larissa.integrativeproject.presentation.viewmodel.MoviesViewModel

class FavoriteMoviesFragment : Fragment(), CheckListener{

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var rvFavoriteMovies: RecyclerView
    private lateinit var favoriteMoviesAdapter: MoviesAdapter
    private lateinit var rvGenres: RecyclerView
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var favoriteMoviesViewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        genreAdapter = GenreAdapter(this, mutableListOf())
        rvGenres = binding.rvGenres
        rvGenres.adapter = genreAdapter
        rvGenres.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        favoriteMoviesAdapter = MoviesAdapter(view.context,this, mutableListOf())
        rvFavoriteMovies = binding.rvMovies
        rvFavoriteMovies.adapter = favoriteMoviesAdapter
        rvFavoriteMovies.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        favoriteMoviesViewModel = ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)
        favoriteMoviesViewModel.getFavoriteMovies()
        favoriteMoviesViewModel.getGenres()
        setupObserveFavoriteMovieList()
        setupObserveGenreList()

    }

    private fun setupObserveFavoriteMovieList() {
        favoriteMoviesViewModel.favoritesLiveData.observe(viewLifecycleOwner) {
            if (this@FavoriteMoviesFragment::favoriteMoviesAdapter.isInitialized) {
                favoriteMoviesAdapter.refreshFavorite(it as MutableList<Movies>)
                favoriteMoviesAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setupObserveGenreList() {
        favoriteMoviesViewModel.genresLiveData.observe(viewLifecycleOwner,
            { genreList ->
                genreAdapter.dataSet.clear()
                genreAdapter.dataSet.addAll(genreList)
                genreAdapter.notifyDataSetChanged()
            }
        )
    }
    override fun onFavoriteCheckListener(movie: Movies, isChecked: Boolean, position: Int) {
         if (isChecked) {
            if (movie.isFavorite) {
                movie.isFavorite = false
                favoriteMoviesViewModel.deleteFavorite(movie)
            }
        }
    }

    override fun onGenreCheckListener(genreIds: MutableList<Int>) {
        favoriteMoviesViewModel.getFavoriteMoviesByGenres(genreIds)
        setupObserveFavoriteMovieList()
    }
//    override fun onErrorListener() {
//        val intent = Intent(context, GenericErrorListener::class.java)
//        startActivity(intent)
//    }
}



