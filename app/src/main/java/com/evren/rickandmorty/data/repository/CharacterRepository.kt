package com.evren.rickandmorty.data.repository

import androidx.paging.PagingData
import com.evren.rickandmorty.data.model.character.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun fetchAllCharacters() : Flow<PagingData<CharacterResponse>>
}