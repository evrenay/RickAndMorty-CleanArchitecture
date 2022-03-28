package com.evren.rickandmorty.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.evren.rickandmorty.R
import com.evren.rickandmorty.databinding.ItemCharacterBinding

class CharacterAdapter (private val onSwipe: () -> Unit) :
    PagingDataAdapter<CharacterItemUiState, CharacterViewHolder>(Comparator) {

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { userItemUiState -> holder.bind(userItemUiState,position) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {

        val binding = inflate<ItemCharacterBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_character,
            parent,
            false
        )

        return CharacterViewHolder(binding,onSwipe)
    }

    object Comparator : DiffUtil.ItemCallback<CharacterItemUiState>() {
        override fun areItemsTheSame(oldItem: CharacterItemUiState, newItem: CharacterItemUiState): Boolean {
            return oldItem.getName() == newItem.getName()
        }

        override fun areContentsTheSame(
            oldItem: CharacterItemUiState,
            newItem: CharacterItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

}