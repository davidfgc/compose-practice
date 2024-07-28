package com.solucionespruna.composepractice.data.pokedex

import com.solucionespruna.composepractice.model.pokedex.Pokemon

class PokeDexRepositoryImpl(
  private val pokeDexServiceAdapter: PokeDexServiceAdapter = PokeDexServiceAdapterImpl()
): PokeDexRepository {

  override suspend fun getPokemonList(): List<Pokemon> {
    return pokeDexServiceAdapter.getPokemonList()
  }

}
