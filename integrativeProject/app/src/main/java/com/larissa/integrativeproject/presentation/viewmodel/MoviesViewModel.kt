package com.larissa.integrativeproject.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.larissa.integrativeproject.data.model.AllMovies
import com.larissa.integrativeproject.data.model.Genre
import com.larissa.integrativeproject.domain.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MoviesViewModel: ViewModel() {

    private val _allMoviesLiveData = MutableLiveData<MutableList<AllMovies>>()
    val allMoviesLiveData : LiveData<MutableList<AllMovies>> = _allMoviesLiveData

    private val _searchMovieLiveData = MutableLiveData<MutableList<AllMovies>>()
    val searchMovieLiveData: MutableLiveData<MutableList<AllMovies>> = _searchMovieLiveData

    private val _genresLiveData = MutableLiveData<MutableList<Genre>>()
    val genresLiveData : MutableLiveData<MutableList<Genre>> = _genresLiveData

    private val _favoritesLiveData = MutableLiveData<MutableList<AllMovies>>()
    val favoritesLiveData: MutableLiveData<MutableList<AllMovies>> = _favoritesLiveData

    private val fetchMoviesUseCase = FetchAllMoviesUseCase()
    private val fetchMovieByGenreUseCase = FetchMoviesByGenreUseCase()
    private val fetchGenresUseCase = FetchGenresUseCase()
    private val fetchMovieByNameUseCase = FetchMovieByNameUseCase()
    private val fetchFavoritesUseCase = FetchFavoritesUseCase()

    fun getMovies(): Disposable {
        return fetchMoviesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
            }
            .subscribe{
                _allMoviesLiveData.value = it.allMoviesList
            }
    }

    fun getMovieByNames(query: Uri): Disposable {
        return fetchMovieByNameUseCase.execute(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
            }
            .subscribe {
                _searchMovieLiveData.value = it.allMoviesList
            }
    }

    fun getMovieByGenres(genreIds: List<Int>): Disposable {
        return fetchMovieByGenreUseCase.execute(genreIds)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
            }
            .subscribe{
                _allMoviesLiveData.value = it.allMoviesList
            }
    }

    fun getGenres(): Disposable {
        return fetchGenresUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
            }
            .subscribe{
                _genresLiveData.value = it.genres
            }
    }
}
