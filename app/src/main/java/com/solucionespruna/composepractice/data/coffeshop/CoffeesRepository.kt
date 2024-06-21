package com.solucionespruna.composepractice.data.coffeshop

import com.solucionespruna.composepractice.ui.coffeshop.home.Coffee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface CoffeesRepository {
  suspend fun getCoffees(): List<Coffee>
}

class CoffeesRepositoryImpl: CoffeesRepository {
  override suspend fun getCoffees() = withContext(Dispatchers.IO) {
    delay(1000)

    (1..10).map {
      Coffee(
        "https://loremflickr.com/400/400/coffee?random$it",
        (Math.random() * 10 % 4 + 1).toFloat(),
        "Caffe Mocha",
        "Deep Foam", (Math.random() * 10).toFloat()
      )
    }
  }
}
