package com.domain

/**
 * Data Class for Pokemon List
 */
data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)