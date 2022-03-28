package com.evren.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.evren.rickandmorty.data.api.CharacterService
import com.evren.rickandmorty.data.datasource.CharacterPagingDataSource
import com.evren.rickandmorty.data.model.character.CharacterResponse
import com.evren.rickandmorty.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val service: CharacterService
) : CharacterRepository {


    override fun fetchAllCharacters(): Flow<PagingData<CharacterResponse>> = Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { CharacterPagingDataSource(service) }
        ).flow



    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

}