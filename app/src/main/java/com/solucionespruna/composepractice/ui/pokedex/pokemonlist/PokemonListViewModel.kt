package com.solucionespruna.composepractice.ui.pokedex.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solucionespruna.composepractice.data.pokedex.PokeDexRepository
import com.solucionespruna.composepractice.data.pokedex.PokeDexRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class PokemonListViewModel(
  private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
  private val pokeDexRepository: PokeDexRepository = PokeDexRepositoryImpl()
): ViewModel() {

  private var _uiState = MutableStateFlow<PokemonListUiState>(PokemonListUiState.Empty)
  val uiState: StateFlow<PokemonListUiState>
    get() = _uiState.asStateFlow()

  private var _pokemonResponseList = MutableStateFlow<List<PokemonViewModelData>>(emptyList())
  val pokemonList: StateFlow<List<PokemonViewModelData>>
    get() = _pokemonResponseList.asStateFlow()

  fun getPokemonList() {
    viewModelScope.launch(mainDispatcher) {
      val res = pokeDexRepository.getPokemonList().map {
        PokemonUIMapper.fromPokemon(it)
      }
      _pokemonResponseList.emit(res)
    }
  }

}

sealed class PokemonListUiState {
  data object Empty: PokemonListUiState()
}

data class PokemonViewModelData(val id: Int, val name: String) {
  val formattedId: String
    get() = String.format(Locale.US, "#%03d", id)
}
