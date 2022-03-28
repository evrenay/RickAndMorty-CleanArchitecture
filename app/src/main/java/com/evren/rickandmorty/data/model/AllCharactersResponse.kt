package com.evren.rickandmorty.data.model

import android.os.Parcelable
import com.evren.rickandmorty.data.model.character.CharacterResponse
import com.evren.rickandmorty.data.model.info.InfoResponse
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AllCharactersResponse(
    val info: InfoResponse?,
    val results: List<CharacterResponse>?
) : Parcelable