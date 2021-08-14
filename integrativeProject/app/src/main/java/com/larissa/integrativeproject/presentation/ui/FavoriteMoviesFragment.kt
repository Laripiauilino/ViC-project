package com.larissa.integrativeproject.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.larissa.integrativeproject.databinding.FragmentMoviesBinding
import com.larissa.integrativeproject.presentation.adapter.MoviesAdapter
import com.larissa.integrativeproject.presentation.viewmodel.FavoriteMoviesViewModel

class FavoriteMoviesFragment : Fragment() {

    private var binding: FragmentMoviesBinding? = null
    private lateinit var rvFavoriteMovies: RecyclerView
    private lateinit var favoriteMoviesAdapter: MoviesAdapter
    private lateinit var favoriteMoviesViewModel: FavoriteMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding?.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView(view)
        favoriteMoviesViewModel.getFavoriteMovies()
        setupObserveFavoriteList()
    }

    private fun setupRecyclerView(view: View) {
        favoriteMoviesAdapter = MoviesAdapter(view.context, mutableListOf())
        rvFavoriteMovies = binding!!.rvMovies
        rvFavoriteMovies.adapter = favoriteMoviesAdapter
        rvFavoriteMovies.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupObserveFavoriteList() {
        favoriteMoviesViewModel.favoritesLiveData.observe(viewLifecycleOwner,
            { favoriteList ->
                favoriteMoviesAdapter.dataSet.clear()
                favoriteMoviesAdapter.dataSet.addAll(favoriteList)
                favoriteMoviesAdapter.notifyDataSetChanged()
            }
        )
    }
}
