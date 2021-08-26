package com.larissa.integrativeproject.data.repository

import com.larissa.integrativeproject.data.model.GenreResponse
import com.larissa.integrativeproject.data.repository.remote.Network
import io.reactivex.Observable

class GenreRepository {

    fun fetchGenreResponse(): Observable<GenreResponse> {
        return Network.getService().getGenreResponse()
    }
}