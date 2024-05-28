package com.data

import com.domain.PokemonList
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API for the PokeAPI service to retrieve Pokemon data.
 */
interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

}
