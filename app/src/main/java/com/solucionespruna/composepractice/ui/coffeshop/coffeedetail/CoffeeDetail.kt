package com.solucionespruna.composepractice.ui.coffeshop.coffeedetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.solucionespruna.composepractice.ComposePracticeApp
import com.solucionespruna.composepractice.ui.common.NavigationIcon
import com.solucionespruna.composepractice.ui.theme.ChangeStatusBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeDetail(coffeeId: Int, onUpClick: () -> Unit, modifier: Modifier = Modifier) {
  ChangeStatusBarColor(isDark = false)
  Scaffold (
    topBar = {
      TopAppBar(
        title = { Text(text = "Detail") },
        navigationIcon = { NavigationIcon(onUpClick) }
      )
    }
  ) {paddingValues ->
    Box(
      modifier = modifier
        .padding(paddingValues)
        .fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Text(text = "Coffee id: $coffeeId")
    }
  }
}

@Preview
@Composable
private fun CoffeeDetailPreview() {
  ComposePracticeApp {
    CoffeeDetail(coffeeId = 9, onUpClick = {})
  }
}
