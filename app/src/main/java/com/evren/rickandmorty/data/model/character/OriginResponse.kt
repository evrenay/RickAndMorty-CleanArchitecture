package com.evren.rickandmorty.data.model.character

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class OriginResponse(
    val name: String?,
    val url: String?
) : Parcelable