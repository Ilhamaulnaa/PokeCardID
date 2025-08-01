package com.ilham.event.data.repository

import com.ilham.event.data.remote.responses.PokemonListResponse
import com.ilham.event.data.remote.responses.PokemonResponse
import com.ilham.event.data.remote.service.PokeApiService
import com.ilham.event.utils.Response
import javax.inject.Inject

open class PokemonRepository @Inject constructor(
    private val api: PokeApiService
) {

    suspend fun getPokemonList(limit: Int, offset: Int): Response<PokemonListResponse> {
        val response = try {
            api.getPokemonList(limit = limit, offset = offset)
        } catch (e: Exception){
            return Response.Error("An known error occured")
        }
        return Response.Success(response)
    }

    open suspend fun getPokemonInfo(pokemonName: String): Response<PokemonResponse> {
        val response = try {
            api.getPokemonInfo(name = pokemonName)
        } catch (e: Exception){
            return Response.Error("An known error occured")
        }
        return Response.Success(response)
    }

}