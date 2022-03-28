package com.evren.rickandmorty.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.evren.rickandmorty.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

        val characterItemsUiStates = characterRepository.fetchAllCharacters()
            .map { pagingData ->
                pagingData.map { characterResponse -> CharacterItemUiState(characterResponse) }
            }.cachedIn(viewModelScope)


}