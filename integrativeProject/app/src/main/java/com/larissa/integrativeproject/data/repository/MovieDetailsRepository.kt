package com.larissa.integrativeproject.data.repository

import com.larissa.integrativeproject.data.model.MovieDetailsResponse
import com.larissa.integrativeproject.data.repository.remote.Network
import io.reactivex.Observable

class MovieDetailsRepository {

    fun fetchMovieDetailsResponse(movieId: Int): Observable<MovieDetailsResponse> {
        return Network.getService().getMovieDetailsResponse(movieId)
    }
}

