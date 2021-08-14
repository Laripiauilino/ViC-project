package com.larissa.integrativeproject.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.larissa.integrativeproject.data.model.Constants
import com.larissa.integrativeproject.databinding.ActivityMovieDetailsBinding
import com.larissa.integrativeproject.presentation.adapter.MoviesAdapter
import com.larissa.integrativeproject.presentation.adapter.CastAdapter
import com.larissa.integrativeproject.presentation.adapter.GenreDetailsAdapter
import com.larissa.integrativeproject.presentation.viewmodel.MovieDetailsViewModel

class MovieDetailsActivity : AppCompatActivity() {

    private var binding: ActivityMovieDetailsBinding? = null
    private lateinit var genreDetailsAdapter: GenreDetailsAdapter
    private lateinit var rvGenreDetails: RecyclerView
    private lateinit var castAdapter: CastAdapter
    private lateinit var rvCasts: RecyclerView
    private var movieDetailsViewModel = MovieDetailsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnReturn?.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        val movieId = intent.extras?.get(MoviesAdapter.MOVIE_ID) as Int

        genreDetailsAdapter = GenreDetailsAdapter(this)
        rvGenreDetails = binding!!.rvGenreDetails
        rvGenreDetails.adapter = genreDetailsAdapter
        rvGenreDetails.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        castAdapter = CastAdapter(this)
        rvCasts = binding!!.rvCast
        rvCasts.adapter = castAdapter
        rvCasts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        movieDetailsViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)

        movieDetailsViewModel.getMovieDetails(movieId)
        movieDetailsViewModel.getCertification(movieId)
        movieDetailsViewModel.getCasts(movieId)
        setupObserveMovieDetailsList()
        setupObserveCastList()
        setupObserveCertification()
    }

    private fun setupObserveMovieDetailsList(){
        movieDetailsViewModel.movieDetailsLiveData.observe(this,
            { movieDetailsList ->
                movieDetailsList?.let {
                    binding?.movieTitleTxt?.text = movieDetailsList.title
                    binding?.movieOverviewTxt?.text = movieDetailsList.overview
                    binding?.movieYearTxt?.text = movieDetailsList.releaseDateFormatted()
                    binding?.movieRuntimeTxt?.text = movieDetailsList.runtimeFormatted()
                    binding?.voteAverageTxt?.text = movieDetailsList.voteAverageFormatted()
                    binding?.favoriteBtn?.isChecked = movieDetailsList.isFavorite

                    Glide.with(this)
                        .load(Constants.BASE_DETAILS_URL.value + movieDetailsList.backdropPath)
                        .into(binding!!.moviePoster)
                    genreDetailsAdapter.dataSet.clear()
                    genreDetailsAdapter.dataSet.addAll(it.genres)
                    genreDetailsAdapter.notifyDataSetChanged()
                }
            }
        )
    }
    private fun setupObserveCastList(){
        movieDetailsViewModel.castsLiveData.observe(this,
            {castList ->
                castList?.let {
                castAdapter.dataSet.clear()
                castAdapter.dataSet.addAll(it.castList)
                castAdapter.notifyDataSetChanged()
                }
            }

        )
    }

    private fun setupObserveCertification(){
        movieDetailsViewModel.certificationResponseLiveData.observe(this,
            {certificationList ->
                certificationList?.let {
                    binding?.movieRealeseDateTxt?.text = certificationList.certificationFormatted()
                }
            }
        )
    }
}





