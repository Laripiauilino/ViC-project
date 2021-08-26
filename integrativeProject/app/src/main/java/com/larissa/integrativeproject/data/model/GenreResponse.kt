package com.larissa.integrativeproject.data.model

import com.google.gson.annotations.SerializedName


data class GenreResponse (
    @SerializedName("genres")
    val results: List<Genre>
)