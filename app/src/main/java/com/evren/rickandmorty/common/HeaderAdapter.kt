package com.evren.rickandmorty.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.evren.rickandmorty.R
import com.evren.rickandmorty.databinding.ItemPagingHeaderBinding


class HeaderAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<HeaderViewHolder>() {
    override fun onBindViewHolder(holder: HeaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): HeaderViewHolder {
        val itemPagingFooterBinding = inflate<ItemPagingHeaderBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_paging_header,
            parent,
            false
        )
        return HeaderViewHolder(itemPagingFooterBinding, retry)
    }

}

