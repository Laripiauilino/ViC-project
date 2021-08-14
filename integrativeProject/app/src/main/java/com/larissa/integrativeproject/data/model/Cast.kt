package com.larissa.integrativeproject.data.model

import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String,
    @SerializedName("character")
    val character: String
)