package com.larissa.integrativeproject.data.repository

import com.larissa.integrativeproject.data.model.Movies
import com.larissa.integrativeproject.data.model.MoviesResponse
import com.larissa.integrativeproject.data.repository.remote.Network
import io.reactivex.Observable

class AllMoviesRepository {
    fun fetchMovieResponse(): Observable<MoviesResponse>{
        return Network.getService().getMovieResponse()
    }

    fun fetchMoviesByName(query: String): Observable<MoviesResponse> {
        return Network.getService().getMoviesByName(query)
    }

    fun fetchMoviesByGenre(genreIds: List<Int>): Observable<MoviesResponse>{
        return Network.getService().getMoviesByGenre(genreIds.joinToString(","))
    }
}