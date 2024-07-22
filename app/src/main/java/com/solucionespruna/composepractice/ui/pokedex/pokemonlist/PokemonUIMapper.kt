package com.solucionespruna.composepractice.ui.pokedex.pokemonlist

import com.solucionespruna.composepractice.model.pokedex.Pokemon

object PokemonUIMapper {
  fun fromPokemon(pokemon: Pokemon) = PokemonViewModelData(
      id = pokemon.id,
      name = pokemon.name
    )
}