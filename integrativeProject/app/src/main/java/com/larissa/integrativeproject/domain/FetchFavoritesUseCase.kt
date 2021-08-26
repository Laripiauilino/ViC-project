package com.larissa.integrativeproject.domain

import android.content.Context
import com.larissa.integrativeproject.data.repository.FavoriteRepository

class FetchFavoritesUseCase (
    private val favoritesRepository: FavoriteRepository = FavoriteRepository()
)
{
     fun execute(context: Context)  = favoritesRepository.fetchFavoriteMovies(context)
}
