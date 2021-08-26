package com.larissa.integrativeproject.domain

import com.larissa.integrativeproject.data.repository.AllMoviesRepository

class FetchMovieByNameUseCase (
    private val moviesByNameRepository: AllMoviesRepository = AllMoviesRepository()
) {
    fun execute(query: String) = moviesByNameRepository.fetchMoviesByName(query)
}

