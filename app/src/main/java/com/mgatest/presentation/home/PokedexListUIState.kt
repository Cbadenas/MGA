package com.mgatest.presentation.home

/**
 * UIState representing [PokemonListScreen] state
 */
data class PokedexListUIState (
    val pokemonList : ArrayList<String> = arrayListOf(),
    val endReached : Boolean = false,
    val isLoading : Boolean = false,
    val loadError : String = ""
)