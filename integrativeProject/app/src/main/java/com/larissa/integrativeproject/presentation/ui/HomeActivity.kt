package com.larissa.integrativeproject.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.larissa.integrativeproject.databinding.ActivityHomeBinding
import com.larissa.integrativeproject.presentation.adapter.MoviesAdapter
import com.larissa.integrativeproject.presentation.adapter.GenreAdapter
import com.larissa.integrativeproject.presentation.adapter.SectionsPagerAdapter
import com.larissa.integrativeproject.presentation.viewmodel.MoviesViewModel


class HomeActivity : AppCompatActivity() {
    private var binding: ActivityHomeBinding? = null
    private lateinit var rvGenres: RecyclerView
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var allMoviesAdapter: MoviesAdapter
    private var moviesViewModel = MoviesViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val viewPager = binding?.viewPager
        viewPager?.adapter = SectionsPagerAdapter(this)
        viewPager?.isUserInputEnabled = false

        val tabLayout = binding?.tabLayout

        TabLayoutMediator(tabLayout!!, viewPager!!) { tab, position ->
            tab.text = when (position) {
                0 ->  TAB_1
                1 ->  TAB_2
                else -> null
                }
            }.attach()
        genreAdapter = GenreAdapter(this, mutableListOf())
        rvGenres = binding!!.rvGenres
        rvGenres.adapter = genreAdapter
        rvGenres.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        moviesViewModel.getGenres()
        setupObserveGenreList()
        searchMovieByGenre()
    }

    companion object {
        private const val TAB_1 = "Todos os Filmes"
        private const val TAB_2 = "Favoritos"
    }

    private fun setupObserveGenreList() {
        moviesViewModel.genresLiveData.observe(this,
            { genreList ->
                genreAdapter.dataSet.clear()
                genreAdapter.dataSet.addAll(genreList)
                genreAdapter.notifyDataSetChanged()
            }
        )
    }

    private fun searchMovieByGenre() {
        genreAdapter.genreChecked = { genreIds ->
            if (genreIds.isEmpty())
                moviesViewModel.getMovies()
            else
                moviesViewModel.getMovieByGenres(genreIds)
        }
    }
}

