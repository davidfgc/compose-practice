package com.solucionespruna.composepractice.ui.pokedex

import com.solucionespruna.composepractice.model.pokedex.Pokemon
import java.util.Locale

object PokemonUIMapper {
  fun fromPokemon(pokemon: Pokemon) = PokemonViewModelData(
      id = String.format(Locale.US, "#%03d", pokemon.id),
      name = pokemon.name
    )
}