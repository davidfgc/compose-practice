package com.solucionespruna.composepractice.ui.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solucionespruna.composepractice.data.pokedex.PokeDexRepository
import com.solucionespruna.composepractice.data.pokedex.PokeDexRepositoryImpl
import com.solucionespruna.composepractice.data.pokedex.Pokemon
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonListViewModel(
  private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
  private val pokeDexRepository: PokeDexRepository = PokeDexRepositoryImpl()
): ViewModel() {

  private var _uiState = MutableStateFlow<PokemonListUiState>(PokemonListUiState.Empty)

  val uiState: StateFlow<PokemonListUiState>
    get() = _uiState.asStateFlow()
  private var _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())

  val pokemonList: StateFlow<List<Pokemon>>
    get() = _pokemonList.asStateFlow()

  fun getPokemonList() {
    viewModelScope.launch(mainDispatcher) {
      val res = pokeDexRepository.getPokemonList()
      _pokemonList.emit(res)
    }
  }

}

sealed class PokemonListUiState {
  data object Empty: PokemonListUiState()
}

