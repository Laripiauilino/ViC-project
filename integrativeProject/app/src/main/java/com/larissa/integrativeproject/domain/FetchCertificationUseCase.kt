package com.larissa.integrativeproject.domain

import com.larissa.integrativeproject.data.repository.CertificationRepository

class FetchCertificationUseCase (
    private val certificationRepository: CertificationRepository = CertificationRepository()
) {
    fun execute(movieId : Int) = certificationRepository.fetchCertificationResponse(movieId)
}