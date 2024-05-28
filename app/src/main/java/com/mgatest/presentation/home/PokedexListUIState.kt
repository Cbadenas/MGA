package com.mgatest.presentation.home

import com.dto.PokedexListEntry

/**
 * UIState representing [PokemonListScreen] state
 */
data class PokedexListUIState (
    val pokemonList : ArrayList<PokedexListEntry> = arrayListOf(),
    val endReached : Boolean = false,
    val isLoading : Boolean = false,
    val loadError : String = ""
)