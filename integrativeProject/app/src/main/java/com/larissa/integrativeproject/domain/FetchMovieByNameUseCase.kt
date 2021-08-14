package com.larissa.integrativeproject.domain

import android.net.Uri
import com.larissa.integrativeproject.data.repository.AllMoviesRepository

class FetchMovieByNameUseCase (
    private val moviesByNameRepository: AllMoviesRepository = AllMoviesRepository()
) {
    fun execute(query: Uri) = moviesByNameRepository.fetchMoviesByName(query)
}

