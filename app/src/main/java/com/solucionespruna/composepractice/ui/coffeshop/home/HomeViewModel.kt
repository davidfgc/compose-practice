package com.solucionespruna.composepractice.ui.coffeshop.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solucionespruna.composepractice.data.coffeshop.CoffeesRepository
import com.solucionespruna.composepractice.data.coffeshop.CoffeesRepositoryImpl
import com.solucionespruna.composepractice.data.coffeshop.RepositoryResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
  private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
  private val coffeesRepository: CoffeesRepository = CoffeesRepositoryImpl()
): ViewModel() {

  private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
  val uiState = _uiState.asStateFlow()

  private val _coffees = MutableStateFlow(listOf<Coffee>())
  val coffees: StateFlow<List<Coffee>> = _coffees.asStateFlow()

  fun getCoffees() {
    viewModelScope.launch(mainDispatcher) {
      _uiState.emit(HomeUiState.Loading)
      when (val res = coffeesRepository.getCoffees()) {
        is RepositoryResult.Success -> {
          _coffees.emit(res.data)
          _uiState.value = run {
            if (res.data.isEmpty()) HomeUiState.Empty
            else HomeUiState.Success
          }
        }
      }
    }
  }

}

sealed class HomeUiState {
  data object Error: HomeUiState()
  data object Loading: HomeUiState()
  data object Empty: HomeUiState()
  data object Success: HomeUiState()
}