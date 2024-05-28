package com.repository

import com.domain.PokemonList
import com.domain.Resourse

/**
 * Contract defined for a Pokemon repository
 */
interface PokemonRepository {

    /**
     * Signature of the method to fetch a list of Pokemon
     * paginated.
     */
    suspend fun getPokemonList(limit: Int, offset: Int): Resourse<PokemonList>

}