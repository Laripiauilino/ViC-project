package com.larissa.integrativeproject.domain

import com.larissa.integrativeproject.data.repository.AllMoviesRepository

class FetchAllMoviesUseCase (
    private val allMoviesRepository: AllMoviesRepository = AllMoviesRepository()
) {
    fun execute() = allMoviesRepository.fetchMovieResponse()
}