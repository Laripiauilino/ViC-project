package com.larissa.integrativeproject.domain

import com.larissa.integrativeproject.data.repository.MovieDetailsRepository

class FetchMovieDetailsUseCase(
    private val movieDetailsRepository: MovieDetailsRepository = MovieDetailsRepository()
) {
    fun execute(movieId : Int) = movieDetailsRepository.fetchMovieDetailsResponse(movieId)
}