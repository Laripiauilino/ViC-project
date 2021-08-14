package com.larissa.integrativeproject.data.repository

import android.net.Uri
import com.larissa.integrativeproject.data.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {

    @GET("genre/movie/list")
    fun getGenreResponse(): Observable<GenreResponse>

    @GET("movie/popular")
    fun getMovieResponse(): Observable<AllMoviesResponse>

    @GET("search/movie")
    fun getMoviesByName(@Query("query") query: Uri): Observable<AllMoviesResponse>

    @GET("discover/movie")
    fun getMoviesByGenre(@Query(value = "with_genres", encoded = true) genreIds: String): Observable<AllMoviesResponse>

    @GET("movie/{movie_id}/credits")
    fun getCastResponse(@Path("movie_id") movieId: Int): Observable<CastResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetailsResponse(@Path("movie_id") movieId: Int): Observable<MovieDetailsResponse>

    @GET("movie/{movie_id}/release_dates")
    fun getCertificationResponse(@Path("movie_id") movieId: Int): Observable<CertificationResponse>






}
