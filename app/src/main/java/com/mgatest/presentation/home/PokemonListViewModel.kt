package com.mgatest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for the [PokemonListScreen].
 */
@HiltViewModel
class PokemonListViewModel @Inject constructor() : ViewModel() {

    /**
     * UI state for the Pokemon list screen.
     */
    private val _state = MutableStateFlow(PokedexListUIState())
    val state = _state.asStateFlow()

    /**
     * Initializes the ViewModel doing a network request
     * to retrieve a list of Pokemon
     * and display the initial loading state.
     */
    init {
        _state.update { state ->
            state.copy(
                isLoading = true
            )
        }
        getPokemonList()
    }

    /**
     * Handles user actions.
     */
    fun onAction(action: PokedexListActions) {
        when (action) {
            PokedexListActions.LoadPodemonList -> {
                getPokemonList()
            }
        }
    }

    /**
     * Retrieves a list of Pokemon from the API.
     */
    private fun getPokemonList() {
        viewModelScope.launch {
            delay(3000)
            _state.update { state ->
                state.copy(
                    isLoading = false
                )
            }
        }
    }

}