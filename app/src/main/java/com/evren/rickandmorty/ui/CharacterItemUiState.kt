package com.evren.rickandmorty.ui

import com.evren.rickandmorty.base.BaseUiState
import com.evren.rickandmorty.data.model.character.CharacterResponse


data class CharacterItemUiState(private val characterResponse: CharacterResponse) : BaseUiState() {

    fun getImageUrl() = characterResponse.image

    fun getName() = characterResponse.name

    fun getStatus() = characterResponse.status

    fun getLocation() = characterResponse.location?.name

}