package com.solucionespruna.composepractice.data.pokedex

class PokeDexRepositoryImpl(
  private val pokeDexServiceAdapter: PokeDexServiceAdapter = PokeDexServiceAdapterImpl()
): PokeDexRepository {

  override suspend fun getPokemonList(): List<Pokemon> {
    return pokeDexServiceAdapter.getPokemonListWith()
  }

}
