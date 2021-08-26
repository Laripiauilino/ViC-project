package com.larissa.integrativeproject.presentation.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.larissa.integrativeproject.R
import com.larissa.integrativeproject.data.model.Movies
import com.larissa.integrativeproject.data.model.Constants
import com.larissa.integrativeproject.databinding.ActivityMovieDetailsBinding
import com.larissa.integrativeproject.domain.CheckListener
import com.larissa.integrativeproject.domain.GenericErrorListener
import com.larissa.integrativeproject.presentation.adapter.MoviesAdapter
import com.larissa.integrativeproject.presentation.adapter.CastAdapter
import com.larissa.integrativeproject.presentation.adapter.GenreDetailsAdapter
import com.larissa.integrativeproject.presentation.viewmodel.MovieDetailsViewModel

class MovieDetailsActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var genreDetailsAdapter: GenreDetailsAdapter
    private lateinit var rvGenreDetails: RecyclerView
    private lateinit var castAdapter: CastAdapter
    private lateinit var rvCasts: RecyclerView
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReturn.setOnClickListener {
            this.finish()
        }

        val movieId = intent.extras?.get(MoviesAdapter.MOVIE_ID) as Int
        val isFavorite = intent.extras?.get(MoviesAdapter.IS_FAVORITE) as Boolean

        genreDetailsAdapter = GenreDetailsAdapter(mutableListOf())
        rvGenreDetails = binding.rvGenreDetails
        rvGenreDetails.adapter = genreDetailsAdapter
        rvGenreDetails.layoutManager =LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)

        castAdapter = CastAdapter(this , mutableListOf())
        rvCasts = binding.rvCast
        rvCasts.adapter = castAdapter
        rvCasts.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)

        movieDetailsViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)

        movieDetailsViewModel.getMovieDetails(movieId)
        movieDetailsViewModel.getCertification(movieId)
        movieDetailsViewModel.getCasts(movieId)
        setupObserveMovieDetailsList()
        setupObserveCastList()
        setupObserveCertification()

        if (isFavorite){
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite_filled)
        }else{
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite_outlined)
        }

    }

    private fun setupObserveMovieDetailsList() {
        movieDetailsViewModel.movieDetailsLiveData.observe(this ,
            { movieDetailsList ->
                movieDetailsList?.let {
                    binding.movieTitleTxt.text = it.title
                    binding.movieOverviewTxt.text = it.overview
                    binding.movieYearTxt.text = it.releaseDateFormatted()
                    binding.movieRuntimeTxt.text = it.runtimeFormatted()
                    binding.voteAverageTxt.text = it.voteAverageFormatted()
                    if(it.backdropPath.isNotEmpty()){
                        Glide.with(this)
                            .load(Constants.BASE_DETAILS_URL.value + it.backdropPath)
                            .into(binding.moviePoster)
                    }else {
                        Glide.with(this)
                            .load(Constants.BASE_POSTER_ALL_MOVIES_URL.value + it.posterPath)
                            .into(binding.moviePoster)}

                    genreDetailsAdapter.dataSet.clear()
                    genreDetailsAdapter.dataSet.addAll(it.genres)
                    genreDetailsAdapter.notifyDataSetChanged()
                }
            }
        )
    }

    private fun setupObserveCastList() {
        movieDetailsViewModel.castsLiveData.observe(this ,
            { castList ->
                castList?.let {
                    castAdapter.dataSet.clear()
                    castAdapter.dataSet.addAll(it.results)
                    castAdapter.notifyDataSetChanged()
                }
            }

        )
    }

    private fun setupObserveCertification() {
        movieDetailsViewModel.certificationResponseLiveData.observe(this ,
            { certificationList ->
                certificationList?.let {
                    binding.movieRealeseDateTxt.text = certificationList.certificationFormatted()
                }
            }
        )
    }

//    override fun onErrorListener() {
//        val intent = Intent(this, GenericErrorListener::class.java)
//        startActivity(intent)
//    }
}





