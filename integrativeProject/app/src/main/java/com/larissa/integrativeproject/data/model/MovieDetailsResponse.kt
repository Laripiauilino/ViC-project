package com.larissa.integrativeproject.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse (
    val genres: List<Genre>,
    @SerializedName("id")
    val movieId: Int,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val runtime: Int,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    var isFavorite: Boolean

){
    fun releaseDateFormatted() : String = releaseDate.take(4)
    fun runtimeFormatted(): String = "%dh %dmin".format(runtime / 60, runtime % 60)
    fun voteAverageFormatted() : String = "${"%.0f".format((voteAverage* 10.0))}%"
}
