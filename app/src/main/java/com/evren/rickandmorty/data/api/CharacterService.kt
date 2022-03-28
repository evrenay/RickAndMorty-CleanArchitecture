package com.evren.rickandmorty.data.api

import androidx.paging.PagingData
import com.evren.rickandmorty.data.model.AllCharactersResponse
import com.evren.rickandmorty.data.model.character.CharacterResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    @GET("character")
    suspend fun fetchAllCharacter(
        @Query("page") page: String
    ): AllCharactersResponse

    @GET("character/{number}")
    suspend fun fetchSingleCharacter(@Path("number") number: String): CharacterResponse
}