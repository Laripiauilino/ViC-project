package com.larissa.integrativeproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.larissa.integrativeproject.data.model.AllMovies
import com.larissa.integrativeproject.data.model.CastResponse
import com.larissa.integrativeproject.data.model.CertificationResponse
import com.larissa.integrativeproject.data.model.MovieDetailsResponse
import com.larissa.integrativeproject.data.repository.FavoritesRepository
import com.larissa.integrativeproject.domain.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsViewModel : ViewModel() {
    //QUEM VÊ É A VIEWMODEL

    private val _movieDetailsLiveData = MutableLiveData<MovieDetailsResponse>()
    val movieDetailsLiveData : LiveData<MovieDetailsResponse> = _movieDetailsLiveData

    private val _favoritesLiveData = MutableLiveData<MutableList<AllMovies>>()
    val favoritesLiveData: MutableLiveData<MutableList<AllMovies>> = _favoritesLiveData

    private val _certificationLiveData = MutableLiveData<CertificationResponse>()
    val certificationResponseLiveData : LiveData<CertificationResponse> = _certificationLiveData

    private val _castsLiveData = MutableLiveData<CastResponse>()
    val castsLiveData : MutableLiveData<CastResponse> = _castsLiveData

    private val fetchMovieDetailsUseCase = FetchMovieDetailsUseCase()
    private val fetchCertificationUseCase = FetchCertificationUseCase()
    private val fetchCastsUseCase = FetchCastsUseCase()
    private val insertFavoriteUseCase = InsertFavoriteUseCase()
    private val deleteFavoriteUseCase = DeleteFavoriteUseCase()

    fun getMovieDetails(movieId: Int):Disposable{
        return fetchMovieDetailsUseCase.execute(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                //Chamar a activity de erro
            }
            .subscribe{
                _movieDetailsLiveData.value = it
            }
    }

    fun getCertification(movieId: Int): Disposable{
        return fetchCertificationUseCase.execute(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                //Chamar a activity de erro
            }
            .subscribe {
                _certificationLiveData.value = it
                }
            }

    fun getCasts(movieId: Int): Disposable{
        return fetchCastsUseCase.execute(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                //Chamar a activity de erro
            }
            .subscribe{
                _castsLiveData.value = it
            }
    }

    fun insertFavorite() {
        _favoritesLiveData.value = insertFavoriteUseCase as MutableList<AllMovies>

    }

    fun removeFavorite() {
        _favoritesLiveData.value = deleteFavoriteUseCase as MutableList<AllMovies>
    }
}

