package com.larissa.integrativeproject.data.model
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
//Testar sem parcelize
@Parcelize
@Entity(tableName = "favoritesTable")
data class Movies(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val movieId: Int = 0,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("poster_path")
    val posterPath: String,
    val title: String ,
    @SerializedName("vote_average")
    val voteAverage: Double ,
    var isFavorite: Boolean

): Parcelable
{
    fun allVoteAverageFormatted(): String = "${"%.0f".format((voteAverage * 10.0))}%"
}