package com.solucionespruna.composepractice.data.pokedex

import com.solucionespruna.composepractice.model.pokedex.Pokemon

interface PokeDexRepository {
  suspend fun getPokemonList(): List<Pokemon>
}
