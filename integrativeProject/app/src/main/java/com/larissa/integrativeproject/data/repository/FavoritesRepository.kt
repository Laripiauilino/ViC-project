package com.larissa.integrativeproject.data.repository

import com.larissa.integrativeproject.data.model.AllMovies

class FavoritesRepository {
    val favoriteMovies = mutableListOf<AllMovies>()

    fun insertFavorite(movie: AllMovies) {
        movie.isFavorite = true
        when(movie){
            !in favoriteMovies ->  favoriteMovies.add(movie)}
        }

    fun deleteFavorite(movie: AllMovies) {
        movie.isFavorite = false
        favoriteMovies.remove(movie)
    }

    fun isFavorite(movie: AllMovies): Boolean {
        return favoriteMovies.contains(movie)
    }

    fun fetchFavoriteMovies(): MutableList<AllMovies> {
        return favoriteMovies
    }
}
