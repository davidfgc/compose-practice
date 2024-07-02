package com.solucionespruna.composepractice.data.pokedex

interface PokeDexRepository {
  suspend fun getPokemonList(): List<Pokemon>
}
