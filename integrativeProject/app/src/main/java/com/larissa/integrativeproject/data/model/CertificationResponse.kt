package com.larissa.integrativeproject.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CertificationResponse(
    val id: Int,
    val results: List<Certification>) : Parcelable {

    @Parcelize
    data class Certification(
        @SerializedName("iso_3166_1")
        val region: String,
        @SerializedName("release_dates")
        val releaseDates: List<ReleaseDate>
    ): Parcelable

    @Parcelize
    data class ReleaseDate(val  certification: String,
                           val type : Int) : Parcelable

    fun certificationFormatted(): String {
        var certFormatted = ""
        for (i in results) {
            if(i.region == "BR") {
                certFormatted += i.releaseDates[0].certification
            }
        }
        return "PG - $certFormatted"
    }
}