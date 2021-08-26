package com.larissa.integrativeproject.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.larissa.integrativeproject.data.model.CastResponse
import com.larissa.integrativeproject.data.model.CertificationResponse
import com.larissa.integrativeproject.data.model.MovieDetailsResponse
import com.larissa.integrativeproject.data.model.Movies
import com.larissa.integrativeproject.domain.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.xml.transform.ErrorListener

class MovieDetailsViewModel(application: Application) : AndroidViewModel(application){

    val context = getApplication<Application>().applicationContext

    private var composite = CompositeDisposable()

    private val _movieDetailsLiveData = MutableLiveData<MovieDetailsResponse>()
    val movieDetailsLiveData: LiveData<MovieDetailsResponse> = _movieDetailsLiveData

    private val _certificationLiveData = MutableLiveData<CertificationResponse>()
    val certificationResponseLiveData: LiveData<CertificationResponse> = _certificationLiveData

    private val _castsLiveData = MutableLiveData<CastResponse>()
    val castsLiveData: LiveData<CastResponse> = _castsLiveData

    private val fetchMovieDetailsUseCase = FetchMovieDetailsUseCase()
    private val fetchCertificationUseCase = FetchCertificationUseCase()
    private val fetchCastsUseCase = FetchCastsUseCase()


    fun getMovieDetails(movieId: Int) {
        composite.add(fetchMovieDetailsUseCase.execute(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
//                errorListener?.onErrorListener()
            }
            .subscribe {
                _movieDetailsLiveData.value = it
                }
            )

    }

    fun getCertification(movieId: Int) {
        composite.add(fetchCertificationUseCase.execute(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
//                errorListener?.onErrorListener()
            }
            .subscribe {
                _certificationLiveData.value = it
            }
        )
    }

    fun getCasts(movieId: Int) {
        composite.add(fetchCastsUseCase.execute(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
//                errorListener?.onErrorListener()
            }
            .subscribe {
                _castsLiveData.value = it
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        composite.dispose()
    }

}

