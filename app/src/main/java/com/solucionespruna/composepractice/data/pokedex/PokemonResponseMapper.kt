package com.solucionespruna.composepractice.data.pokedex

import com.solucionespruna.composepractice.model.pokedex.Pokemon

object PokemonResponseMapper {
  fun toPokemon(pokemonResponse: PokemonResponse): Pokemon {
    val id = pokemonResponse.url.dropLast(1).takeLastWhile { it != '/' }.toInt()

    return Pokemon(id, pokemonResponse.name, pokemonResponse.url)
  }
}