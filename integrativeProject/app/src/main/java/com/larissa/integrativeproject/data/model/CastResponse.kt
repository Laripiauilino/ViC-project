package com.larissa.integrativeproject.data.model

import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("cast")
    val results: List<Cast>
)