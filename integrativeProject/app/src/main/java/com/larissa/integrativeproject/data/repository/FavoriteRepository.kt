package com.larissa.integrativeproject.data.repository

import android.content.Context
import com.larissa.integrativeproject.data.model.Movies
import com.larissa.integrativeproject.data.repository.local.DatabaseBuilder
import com.larissa.integrativeproject.data.repository.local.FavoriteDatabase
import io.reactivex.Observable
import io.reactivex.Single


class FavoriteRepository {

    private var db: FavoriteDatabase? = null

    fun insertFavoriteMovie(context: Context , favoriteMovie: Movies) {
        db = DatabaseBuilder.getFavoriteDatabase(context)
        db?.favoriteDao()?.insertFavorite(favoriteMovie)
    }

    fun deleteFavoriteMovie(context: Context , favoriteMovie: Movies) {
        db = DatabaseBuilder.getFavoriteDatabase(context)
        db?.favoriteDao()?.deleteFavorite(favoriteMovie)
    }

    fun fetchFavoriteMovies(context: Context): MutableList<Movies>? {
        var favoritesList: MutableList<Movies>?
        db = DatabaseBuilder.getFavoriteDatabase(context)
        val favoriteDao = db?.favoriteDao()
        favoritesList = favoriteDao?.getFavorites()

        return favoritesList

    }
}







