package com.larissa.integrativeproject.domain

import com.larissa.integrativeproject.data.repository.CastRepository

class FetchCastsUseCase (
    private val castRepository: CastRepository = CastRepository()
) {
    fun execute(movieId: Int) = castRepository.fetchCastResponse(movieId)
}