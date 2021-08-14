package com.larissa.integrativeproject.data.model
import com.google.gson.annotations.SerializedName

data class AllMovies(
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val movieId: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    var isFavorite: Boolean

) {
    fun allVoteAverageFormatted(): String = "${"%.0f".format((voteAverage * 10.0))}%"
}