package com.larissa.integrativeproject.domain

import android.content.Context
import com.larissa.integrativeproject.data.model.Movies
import com.larissa.integrativeproject.data.repository.FavoriteRepository

class InsertFavoriteUseCase(private val favoritesRepository: FavoriteRepository = FavoriteRepository())
 { fun execute(context: Context,favoriteMovie: Movies) = favoritesRepository.insertFavoriteMovie(context,favoriteMovie)
}
