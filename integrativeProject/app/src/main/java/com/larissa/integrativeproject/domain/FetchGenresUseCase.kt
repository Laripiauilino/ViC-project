package com.larissa.integrativeproject.domain

import com.larissa.integrativeproject.data.repository.GenreRepository

class FetchGenresUseCase (
    private val genreRepository: GenreRepository = GenreRepository()
) {
    fun execute() = genreRepository.fetchGenreResponse()
}