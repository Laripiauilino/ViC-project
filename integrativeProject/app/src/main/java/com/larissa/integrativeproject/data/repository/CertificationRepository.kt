package com.larissa.integrativeproject.data.repository

import com.larissa.integrativeproject.data.model.CertificationResponse
import com.larissa.integrativeproject.data.repository.remote.Network
import io.reactivex.Observable

class CertificationRepository {
    fun fetchCertificationResponse( movieId: Int): Observable<CertificationResponse> {
        return Network.getService().getCertificationResponse(movieId)
    }
}