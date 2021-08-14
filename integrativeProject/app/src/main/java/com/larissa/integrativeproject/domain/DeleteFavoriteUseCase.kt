package com.larissa.integrativeproject.domain

import com.larissa.integrativeproject.data.model.AllMovies
import com.larissa.integrativeproject.data.repository.FavoritesRepository

class DeleteFavoriteUseCase (
    private val favoritesRepository: FavoritesRepository = FavoritesRepository()
) {
    fun execute(movie: AllMovies) = favoritesRepository.deleteFavorite(movie)
}

