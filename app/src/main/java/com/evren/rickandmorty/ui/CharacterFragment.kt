package com.evren.rickandmorty.ui

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.viewpager2.widget.ViewPager2
import com.evren.rickandmorty.R
import com.evren.rickandmorty.base.BaseFragment
import com.evren.rickandmorty.common.HeaderAdapter
import com.evren.rickandmorty.databinding.FragmentCharacterBinding
import com.evren.rickandmorty.util.ext.collect
import com.evren.rickandmorty.util.ext.collectLast
import com.evren.rickandmorty.util.ext.executeWithAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>() {

    override val layoutRes: Int = R.layout.fragment_character

    private val viewModel: CharacterViewModel by viewModels()

    lateinit var characterAdapter: CharacterAdapter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setAdapter()


        collect(flow = characterAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setCharactersUiState
        )
        binding.viewpager.apply {
            adapter = characterAdapter.withLoadStateHeader(HeaderAdapter(characterAdapter::retry))
            binding.viewpager.isUserInputEnabled = false
            offscreenPageLimit = 2
            setPageTransformer(SliderTransformer(2))
        }



        collectLast(viewModel.characterItemsUiStates,::setCharacters)
    }



    private fun setListener() {
        binding.btnRetry.setOnClickListener { characterAdapter.retry() }
    }


    private fun setAdapter() {
        characterAdapter = CharacterAdapter() {
            binding.viewpager.setCurrentItem(binding.viewpager.currentItem+1, false)
        }
        binding.viewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val motionLayout = (binding.viewpager.findViewWithTag<View>(position.toString()) as MotionLayout)
                binding.likeFloating.setOnClickListener {
                    motionLayout.transitionToState(R.id.like)
                }

                binding.unlikeFloating.setOnClickListener {
                    motionLayout.transitionToState(R.id.unlike)
                }
            }
        })

    }

    private fun setCharactersUiState(loadState: LoadState) {
        binding.executeWithAction {
            charactersUiState = CharactersUiState(loadState)
        }
    }

    private suspend fun setCharacters(characterItemsPagingData: PagingData<CharacterItemUiState>) {
        characterAdapter.submitData(characterItemsPagingData)
    }

}