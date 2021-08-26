package com.larissa.integrativeproject.presentation.viewmodel

import android.app.Application
import android.graphics.Movie
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.map
import com.larissa.integrativeproject.data.model.Movies
import com.larissa.integrativeproject.data.model.Genre
import com.larissa.integrativeproject.data.model.MoviesResponse
import com.larissa.integrativeproject.domain.*
import io.reactivex.Flowable.fromIterable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MoviesViewModel (application: Application) : AndroidViewModel(application){

    private val composite = CompositeDisposable()
    private var disposable: Disposable? = null

    val context = getApplication<Application>().applicationContext

    private lateinit var allMoviesList : List<Movies>
    private lateinit var searchedMoviesList : List<Movies>
    private lateinit var favoriteMoviesList : List<Movies>

    private val _genresLiveData = MutableLiveData<List<Genre>>()
    val genresLiveData: MutableLiveData<List<Genre>> = _genresLiveData

    private val _allMoviesLiveData = MutableLiveData<List<Movies>>()
    val moviesLiveData: LiveData<List<Movies>> = _allMoviesLiveData

    private val _favoritesLiveData = MutableLiveData<List<Movies>>()
    val favoritesLiveData: MutableLiveData<List<Movies>> = _favoritesLiveData

    private val _searchMovieLiveData = MutableLiveData<List<Movies>>()
    val searchMovieLiveData: MutableLiveData<List<Movies>> = _searchMovieLiveData

    private val fetchMoviesUseCase = FetchAllMoviesUseCase()
    private val fetchGenresUseCase = FetchGenresUseCase()

    private val fetchMovieByGenreUseCase = FetchMoviesByGenreUseCase()
    private val fetchFavoritesUseCase = FetchFavoritesUseCase()
    private val deleteFavoriteUseCase = DeleteFavoriteUseCase()
    private val insertFavoriteUseCase = InsertFavoriteUseCase()
    private val fetchMovieByNameUseCase = FetchMovieByNameUseCase()

    fun getGenres(){
        val service = fetchGenresUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
//                errorListener?.onErrorListener()
            }
            .subscribe {
                _genresLiveData.setValue(it.results)
            }
    }

    fun getAllMovies() {
        val service = fetchMoviesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
//                errorListener?.onErrorListener()
            }
            .subscribe {
                allMoviesList = it.results
                _allMoviesLiveData.setValue(it.results)
                updateFavoriteStatus(it.results)
            }
    }

    fun getAllMovieByGenres(genreIds: List<Int>) {
        composite.add(fetchMovieByGenreUseCase.execute(genreIds)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
//                errorListener?.onErrorListener()
            }
            .subscribe {
                _allMoviesLiveData.setValue(it.results)
                updateFavoriteStatus(it.results)
            }
        )
    }

    fun getFavoriteMovies(): List<Movies>?{
        var response: List<Movies>? = null
        CoroutineScope(Dispatchers.IO).launch {
            response = fetchFavoritesUseCase.execute(context)
            _favoritesLiveData.postValue(response?.toMutableList())
            updateFavoriteStatus(response)
        }
        return response
    }

    fun insertFavorite(movie: Movies) {
        CoroutineScope(Dispatchers.IO).launch{
            insertFavoriteUseCase.execute(context , movie)
            getFavoriteMovies()
        }
    }

    fun deleteFavorite(movie: Movies) {
        CoroutineScope(Dispatchers.IO).launch{
            deleteFavoriteUseCase.execute(context, movie)
            getFavoriteMovies()
        }
    }

    fun getFavoriteMoviesByGenres(genresList: List<Int>) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = fetchFavoritesUseCase.execute(context)

            if (genresList.isNotEmpty()) {
                val searchedMovies = response?.filter { movie ->
                    movie.genreIds.containsAll(genresList)
                }

                _favoritesLiveData.postValue(searchedMovies?.toMutableList())

            } else {
                _favoritesLiveData.postValue(response?.toMutableList())
            }
        }
    }

    fun getMoviesByName(query: String){
        composite.add(fetchMovieByNameUseCase.execute(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
//                errorListener?.onErrorListener()
            }
            .subscribe {
                _searchMovieLiveData.setValue(it.results)
                updateFavoriteStatus(it.results)
            }
        )
    }

    fun getSearchedMoviesByGenres(listSearchMovieByGenres: List<Int>) {

        if (listSearchMovieByGenres.isNotEmpty()) {
            val filteredMovies = searchedMoviesList.filter { movie ->
                movie.genreIds.containsAll(listSearchMovieByGenres)
            }
            updateFavoriteStatus(filteredMovies)

        } else {
            updateFavoriteStatus(searchedMoviesList)
        }
    }

    private fun updateFavoriteStatus(listFavoriteMovies: List<Movies>?) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = fetchFavoritesUseCase.execute(context)

            val favoriteMoviesList = listFavoriteMovies?.filter { movie ->
                response?.find { it.movieId == movie.movieId} != null
            }
            _allMoviesLiveData.postValue(listFavoriteMovies?.map { movie ->
                if (favoriteMoviesList?.find { it.movieId == movie.movieId } != null) {
                    movie.isFavorite = true
                }
                movie
            })
        }
    }

    override fun onCleared() {
        composite.dispose()
        super.onCleared()
    }
}






