package com.solucionespruna.composepractice.data.pokedex

import com.solucionespruna.composepractice.model.pokedex.Pokemon
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface PokeDexServiceAdapter {
  suspend fun getPokemonList(): List<Pokemon>
}

open class PokeDexServiceAdapterImpl: PokeDexServiceAdapter {

  private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
      .baseUrl("https://pokeapi.co/api/v2/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }
  private val pokemonService by lazy {
    retrofit.create(PokeDexService::class.java)
  }

  override suspend fun getPokemonList(): List<Pokemon> {

    return pokemonService.getPokemonList().results.map {
      PokemonResponseMapper.toPokemon(it)
    }
  }

}
