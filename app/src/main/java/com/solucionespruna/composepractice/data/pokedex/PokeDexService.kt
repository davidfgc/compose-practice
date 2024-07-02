package com.solucionespruna.composepractice.data.pokedex

import retrofit2.http.GET

interface PokeDexService {
  @GET("pokemon")
  suspend fun getPokemonList(): PokemonListResponse
}

data class PokemonListResponse(val count: Int, val next: String, val previous: String, val results: List<Pokemon>)