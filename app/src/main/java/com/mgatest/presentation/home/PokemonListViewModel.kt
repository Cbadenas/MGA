package com.mgatest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Constants.PAGE_SIZE
import com.domain.Resourse
import com.dto.PokedexListEntry
import com.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

/**
 * ViewModel class for the [PokemonListScreen].
 */
@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private var curPage = 0

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
            _state.update { state ->
                state.copy(
                    isLoading = true
                )
            }

            when (val response = repository.getPokemonList(PAGE_SIZE, curPage * PAGE_SIZE)) {
                is Resourse.Success -> {
                    val pokedexEntries = response.data!!.results.mapIndexed { index, entry ->
                        val number = if (entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val url =
                            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokedexListEntry(entry.name.capitalize(Locale.ROOT), url, number.toInt())
                    }
                    curPage++

                    _state.update { state ->
                        state.copy(
                            pokemonList = (state.pokemonList + pokedexEntries) as ArrayList<PokedexListEntry>,
                            endReached = curPage * PAGE_SIZE >= response.data!!.count,
                            loadError = "",
                            isLoading = false
                        )
                    }
                }

                is Resourse.Error -> {
                    _state.update { state ->
                        state.copy(
                            loadError = response.message!!,
                            isLoading = false
                        )
                    }
                }

                is Resourse.Loading -> {
                    /* NOOP NOW */
                }
            }

        }
    }

}