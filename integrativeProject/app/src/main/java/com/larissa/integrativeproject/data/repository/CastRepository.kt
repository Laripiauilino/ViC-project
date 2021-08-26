package com.larissa.integrativeproject.data.repository
import com.larissa.integrativeproject.data.model.CastResponse
import com.larissa.integrativeproject.data.repository.remote.Network
import io.reactivex.Observable

class CastRepository {
    fun fetchCastResponse(movieId: Int): Observable<CastResponse>{
        return Network.getService().getCastResponse(movieId)
    }
}