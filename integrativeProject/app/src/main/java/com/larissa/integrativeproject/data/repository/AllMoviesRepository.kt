package com.larissa.integrativeproject.data.repository

import android.net.Uri
import com.larissa.integrativeproject.data.model.AllMoviesResponse
import io.reactivex.Observable

class AllMoviesRepository {
    fun fetchMovieResponse(): Observable<AllMoviesResponse>{
        return Network.getService().getMovieResponse()
    }

    fun fetchMoviesByName(query: Uri): Observable<AllMoviesResponse> {
        return Network.getService().getMoviesByName(query)
    }

    fun fetchMoviesByGenre(genreIds: List<Int>): Observable<AllMoviesResponse> {
        return Network.getService().getMoviesByGenre(genreIds.joinToString(","))
    }
}