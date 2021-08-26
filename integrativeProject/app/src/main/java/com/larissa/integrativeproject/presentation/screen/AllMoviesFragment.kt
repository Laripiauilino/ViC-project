package com.larissa.integrativeproject.presentation.screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

class AllMoviesFragment : Fragment(), CheckListener{

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var rvAllMovies: RecyclerView
    private lateinit var allMoviesAdapter: MoviesAdapter
    private lateinit var rvGenres: RecyclerView
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var moviesViewModel: MoviesViewModel
    private var checkFavorite: Boolean? = null


    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {

        genreAdapter = GenreAdapter(this, mutableListOf())
        rvGenres = binding.rvGenres
        rvGenres.adapter = genreAdapter
        rvGenres.layoutManager = LinearLayoutManager(view.context , LinearLayoutManager.HORIZONTAL , false)

        allMoviesAdapter = MoviesAdapter(view.context , this, mutableListOf())
        rvAllMovies = binding.rvMovies
        rvAllMovies.adapter = allMoviesAdapter
        rvAllMovies.layoutManager = LinearLayoutManager(view.context , LinearLayoutManager.HORIZONTAL , false)

        moviesViewModel = ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)
        moviesViewModel.getAllMovies()
        moviesViewModel.getGenres()

        setupObserveGenreList()
        setupObserveMovieList()
    }

    private fun setupObserveMovieList() {
        moviesViewModel.moviesLiveData.observe(viewLifecycleOwner,
            { movieList ->
                allMoviesAdapter.dataSet.clear()
                allMoviesAdapter.dataSet.addAll(movieList)
                allMoviesAdapter.notifyDataSetChanged()
            }
        )

    }

    private fun setupObserveGenreList() {
        moviesViewModel.genresLiveData.observe(viewLifecycleOwner ,
            { genreList ->
                genreAdapter.dataSet.clear()
                genreAdapter.dataSet.addAll(genreList)
                genreAdapter.notifyDataSetChanged()

            }
        )
    }

    override fun onFavoriteCheckListener(movie: Movies, isChecked: Boolean, position: Int) {
        if (isChecked) {
            if (!movie.isFavorite) {
                movie.isFavorite = true
                moviesViewModel.insertFavorite(movie)
            } else {
                movie.isFavorite = false
                moviesViewModel.deleteFavorite(movie)
            }
            allMoviesAdapter.notifyItemChanged(position)

        }
    }

    override fun onGenreCheckListener(genreIds: MutableList<Int>) {
        moviesViewModel.getAllMovieByGenres(genreIds)
    }

//    override fun onErrorListener() {
//        val intent = Intent(context, GenericErrorListener::class.java)
//        startActivity(intent)
//    }
}
