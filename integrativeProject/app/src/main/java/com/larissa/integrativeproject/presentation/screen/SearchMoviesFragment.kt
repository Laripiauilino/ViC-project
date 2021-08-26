package com.larissa.integrativeproject.presentation.screen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.larissa.integrativeproject.data.model.Movies
import com.larissa.integrativeproject.databinding.FragmentSearchMoviesBinding
import com.larissa.integrativeproject.domain.CheckListener
import com.larissa.integrativeproject.domain.GenericErrorListener
import com.larissa.integrativeproject.presentation.adapter.GenreAdapter
import com.larissa.integrativeproject.presentation.adapter.MoviesAdapter
import com.larissa.integrativeproject.presentation.viewmodel.MoviesViewModel


class SearchMoviesFragment : Fragment(), CheckListener {

    private lateinit var binding: FragmentSearchMoviesBinding
    private lateinit var rvSearchMovies: RecyclerView
    private lateinit var searchMoviesAdapter: MoviesAdapter
    private lateinit var rvGenres: RecyclerView
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var searchMoviesViewModel: MoviesViewModel
    private var query: String? = null
    private var genreIds: List<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            query = it.getString(ARG_QUERY)
        }
    }

    companion object {

        private const val ARG_QUERY = "query"

        @JvmStatic
        fun newInstance(query: String) =
            SearchMoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_QUERY, query)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        genreAdapter = GenreAdapter(this, mutableListOf())
        rvGenres = binding.rvGenres
        rvGenres.adapter = genreAdapter
        rvGenres.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        searchMoviesAdapter = MoviesAdapter(view.context, this, mutableListOf())
        rvSearchMovies = binding.rvMovies
        rvSearchMovies.adapter = searchMoviesAdapter
        rvSearchMovies.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        searchMoviesViewModel =
            ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)

        updateSearch(query.toString())
    }

    fun updateSearch(query: String) {
        searchMoviesViewModel.getMoviesByName(query)
        searchMoviesViewModel.getGenres()
        setupObserveGenreList()
        setupObserveSearchList()
    }

    private fun setupObserveSearchList() {
        searchMoviesViewModel.searchMovieLiveData.observe(viewLifecycleOwner,
            { movieList ->
                movieList?.let {
                    if (movieList.isEmpty()) {
                        binding.notFound.visibility = View.VISIBLE
                        binding.rvGenres.visibility = View.INVISIBLE
                        searchMoviesAdapter.dataSet.clear()
                        searchMoviesAdapter.notifyDataSetChanged()
                    } else {
                        binding.notFound.visibility = View.INVISIBLE
                        binding.rvMovies.visibility = View.VISIBLE
                        binding.rvGenres.visibility = View.VISIBLE

                        searchMoviesAdapter.dataSet = it as MutableList<Movies>
                        searchMoviesAdapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }

    private fun setupObserveGenreList() {
        searchMoviesViewModel.genresLiveData.observe(viewLifecycleOwner,
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
                searchMoviesViewModel.insertFavorite(movie)
            } else {
                movie.isFavorite = false
                searchMoviesViewModel.deleteFavorite(movie)
            }
            searchMoviesAdapter.notifyItemChanged(position)
        }
    }

    override fun onGenreCheckListener(genreIds: MutableList<Int>) {
        searchMoviesViewModel.getSearchedMoviesByGenres(genreIds)
    }

//    override fun onErrorListener() {
//        val intent = Intent(context, GenericErrorListener::class.java)
//        startActivity(intent)
//    }
}