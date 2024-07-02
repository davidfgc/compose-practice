package com.solucionespruna.composepractice.data.pokedex

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeDexServiceAdapter {

  private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
      .baseUrl("https://pokeapi.co/api/v2/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }
  private val pokemonService by lazy {
    retrofit.create(PokeDexService::class.java)
  }

  suspend fun getPokemonListWith(): List<Pokemon> {

    return pokemonService.getPokemonList().results
  }

}
