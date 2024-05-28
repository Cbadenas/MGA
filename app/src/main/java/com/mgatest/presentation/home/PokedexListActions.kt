package com.mgatest.presentation.home

/**
 * Actions for [PokemonListScreen]
 */
sealed interface PokedexListActions {

    /**
     * Event for error handling.
     * If no connection of some kind of error occurs,
     * Retry button will be shown and tapping with trigger [LoadPodemonList]
     */
    object LoadPodemonList : PokedexListActions

}