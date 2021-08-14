package com.larissa.integrativeproject.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.larissa.integrativeproject.data.model.AllMovies
import com.larissa.integrativeproject.data.repository.FavoritesRepository
import com.larissa.integrativeproject.domain.*
import io.reactivex.Flowable.fromIterable
import io.reactivex.Observable.fromIterable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class FavoriteMoviesViewModel : ViewModel() {

    private val _favoritesLiveData = MutableLiveData<MutableList<AllMovies>>()
    val favoritesLiveData: MutableLiveData<MutableList<AllMovies>> = _favoritesLiveData

    private val fetchFavoritesUseCase = FetchFavoritesUseCase()

    fun getFavoriteMovies() {
        val favoritesData = fetchFavoritesUseCase
        _favoritesLiveData.value = favoritesData as MutableList<AllMovies>
    }
}