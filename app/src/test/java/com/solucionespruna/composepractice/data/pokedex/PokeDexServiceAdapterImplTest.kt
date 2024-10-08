package com.solucionespruna.composepractice.data.pokedex

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PokeDexServiceAdapterImplTest {

  @Test
  fun `getPokemonListWith with 0 expects 20 items`() = runTest {
    val pokemonList = PokeDexServiceAdapterImpl().getPokemonList()

    assertEquals(20, pokemonList.count())
  }

}