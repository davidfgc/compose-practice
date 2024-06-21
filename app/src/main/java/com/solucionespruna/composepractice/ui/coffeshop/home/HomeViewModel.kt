package com.solucionespruna.composepractice.ui.coffeshop.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
  private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
): ViewModel() {

  private val _coffees = MutableStateFlow(listOf<CoffeeItem>())
  val coffees: StateFlow<List<CoffeeItem>> = _coffees.asStateFlow()

  fun getCoffees() {
    val coffees = (1..10).map {
      CoffeeItem(
        "https://loremflickr.com/400/400/coffee?random$it",
        (Math.random() * 10 % 4 + 1).toFloat(),
        "Caffe Mocha",
        "Deep Foam", (Math.random() * 10).toFloat()
      )
    }
    viewModelScope.launch(mainDispatcher) {
      _coffees.emit(coffees)
    }
  }

}