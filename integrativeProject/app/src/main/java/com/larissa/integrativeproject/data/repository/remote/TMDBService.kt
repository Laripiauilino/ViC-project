package com.larissa.integrativeproject.data.repository.remote

import com.larissa.integrativeproject.data.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {

    @GET("genre/movie/list")
    fun getGenreResponse(): Observable<GenreResponse>

    @GET("movie/popular")
    fun getMovieResponse(): Observable<MoviesResponse>

    @GET("search/movie")
    fun getMoviesByName(@Query("query", encoded = true) query: String): Observable<MoviesResponse>

    @GET("discover/movie")
    fun getMoviesByGenre(@Query( "with_genres", encoded = true) genreIds: String): Observable<MoviesResponse>

    @GET("movie/{movie_id}/credits")
    fun getCastResponse(@Path("movie_id") movieId: Int): Observable<CastResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetailsResponse(@Path("movie_id") movieId: Int): Observable<MovieDetailsResponse>

    @GET("movie/{movie_id}/release_dates")
    fun getCertificationResponse(@Path("movie_id") movieId: Int): Observable<CertificationResponse>






}
