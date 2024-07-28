package com.solucionespruna.composepractice.ui.pokedex

import com.solucionespruna.composepractice.data.pokedex.PokeDexAdapterSuccessStub
import com.solucionespruna.composepractice.data.pokedex.PokeDexRepository
import com.solucionespruna.composepractice.model.pokedex.Pokemon
import com.solucionespruna.composepractice.ui.pokedex.pokemonlist.PokemonListUiState
import com.solucionespruna.composepractice.ui.pokedex.pokemonlist.PokemonListViewModel
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PokemonResponseListViewModelTest {

  private val testDispatcher = UnconfinedTestDispatcher()

  @Test
  fun `initial state`() {
    val viewModel = PokemonListViewModel()

    assertTrue(viewModel.uiState.value is PokemonListUiState.Empty)
    assertTrue(viewModel.pokemonList.value.isEmpty())
  }

  @Test
  fun `getPokemonList with success stub expects 10 item`() = runTest {
    val viewModel = PokemonListViewModel(testDispatcher, PokeDexRepositorySuccessStub())

    viewModel.getPokemonList()
    assertEquals(10, viewModel.pokemonList.value.count())
  }

}

class PokeDexRepositorySuccessStub: PokeDexRepository {
  override suspend fun getPokemonList(): List<Pokemon> {
    return PokeDexAdapterSuccessStub().getPokemonList()
  }
}