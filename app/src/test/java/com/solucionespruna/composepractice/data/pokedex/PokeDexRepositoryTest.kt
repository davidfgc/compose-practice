package com.solucionespruna.composepractice.data.pokedex

import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class PokeDexRepositoryTest {

  @Test
  fun `getPokemonList with Success adapter response expects 1 items`() = runTest {
    val pokeDexServiceAdapter: PokeDexServiceAdapter = PokeDexAdapterSuccessStub()

    val res = PokeDexRepositoryImpl(pokeDexServiceAdapter).getPokemonList()

    Assert.assertEquals(10, res.count())
  }
}

class PokeDexAdapterSuccessStub(
  private val items: List<PokemonResponse> = getFakePokemonList()
): PokeDexServiceAdapter {

  override suspend fun getPokemonListWith(): List<Any> {
    return items
  }

}

private fun getFakePokemonList(items: Int = 10) = (1..items).map {
  PokemonResponse("name $it", "url $it")
}