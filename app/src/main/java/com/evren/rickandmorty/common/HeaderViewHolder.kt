package com.evren.rickandmorty.common

import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.evren.rickandmorty.databinding.ItemPagingHeaderBinding
import com.evren.rickandmorty.util.ext.executeWithAction

class HeaderViewHolder(
    private val binding: ItemPagingHeaderBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        binding.executeWithAction {
            headerUiState = HeaderUiState(loadState)
        }
    }
}

