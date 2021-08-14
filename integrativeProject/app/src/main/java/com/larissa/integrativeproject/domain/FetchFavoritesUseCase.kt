package com.larissa.integrativeproject.domain

import com.larissa.integrativeproject.data.repository.FavoritesRepository

class FetchFavoritesUseCase (
    private val favoritesRepository: FavoritesRepository = FavoritesRepository()
) {
    fun execute() = favoritesRepository.fetchFavoriteMovies()
}