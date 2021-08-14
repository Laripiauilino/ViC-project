package com.larissa.integrativeproject.domain

import com.larissa.integrativeproject.data.repository.AllMoviesRepository

class FetchMoviesByGenreUseCase (
    private val moviesByGenreRepository: AllMoviesRepository = AllMoviesRepository()
) {
    fun execute(genreIds: List<Int>) = moviesByGenreRepository.fetchMoviesByGenre(genreIds)
}
