package com.data.remote

import com.data.PokeApi
import com.domain.PokemonList
import com.domain.Resourse
import com.repository.PokemonRepository
import javax.inject.Inject

/**
 * Implementation of PokemonRepository
 */
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi
) : PokemonRepository {

    /**
     * Implementation of getPokemonList method that returns a list of Pokemon
     * from the PokeApi
     */
    override suspend fun getPokemonList(limit: Int, offset: Int): Resourse<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resourse.Error("Something was wrong")
        }

        return Resourse.Success(response)
    }
}