package com.evren.rickandmorty.ui

import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.motion.widget.KeyPosition
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.evren.rickandmorty.R
import com.evren.rickandmorty.databinding.ItemCharacterBinding
import com.evren.rickandmorty.util.ext.executeWithAction


class CharacterViewHolder(private val binding: ItemCharacterBinding,private val onSwipe: () -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(characterItemUiState: CharacterItemUiState,position: Int) {
        binding.root.tag = position.toString()
        binding.motionLayout.progress = 0f
        binding.motionLayout.setTransition(R.id.start, R.id.detail)
        binding.motionLayout.setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                when (currentId) {
                    R.id.offScreenUnlike,
                    R.id.offScreenLike -> {
                        onSwipe()
                    }
                }
            }
        })
        binding.executeWithAction {
            this.characterItemUiState = characterItemUiState
        }

    }
}