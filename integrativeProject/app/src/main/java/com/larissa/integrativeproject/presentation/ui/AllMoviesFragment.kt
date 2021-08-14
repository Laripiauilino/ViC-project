package com.larissa.integrativeproject.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.larissa.integrativeproject.databinding.FragmentMoviesBinding
import com.larissa.integrativeproject.presentation.adapter.MoviesAdapter
import com.larissa.integrativeproject.presentation.viewmodel.MoviesViewModel

class AllMoviesFragment : Fragment() {

    private var binding: FragmentMoviesBinding? = null

    private lateinit var rvAllMovies: RecyclerView
    private lateinit var allMoviesAdapter: MoviesAdapter
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        allMoviesAdapter = MoviesAdapter(view.context, mutableListOf())
        rvAllMovies = binding!!.rvMovies
        rvAllMovies.adapter = allMoviesAdapter
        rvAllMovies.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)


        moviesViewModel = ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)
        moviesViewModel.getMovies()
        moviesViewModel.getGenres()
        setupObserveMovieList()
    }

    private fun setupObserveMovieList() {
        moviesViewModel.allMoviesLiveData.observe(viewLifecycleOwner,
            { movieList ->
                allMoviesAdapter.dataSet.clear()
                allMoviesAdapter.dataSet.addAll(movieList)
                allMoviesAdapter.notifyDataSetChanged()
            }
        )
    }
}