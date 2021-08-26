package com.larissa.integrativeproject.data.repository.local

import androidx.room.*
import com.larissa.integrativeproject.data.model.Movies
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.Deferred

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(movie: Movies)

    @Delete
    fun deleteFavorite(movie: Movies)

    @Query("SELECT * FROM favoritesTable")
    fun getFavorites(): MutableList<Movies>
}