package com.solucionespruna.composepractice.ui.coffeshop.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.solucionespruna.composepractice.ComposePracticeApp
import com.solucionespruna.composepractice.ui.coffeshop.common.IconButtonPrimary
import com.solucionespruna.composepractice.ui.theme.ChangeStatusBarColor

@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  homeViewModel: HomeViewModel = viewModel(),
  isDark: Boolean = true,
  onCoffeeClicked: (Int) -> Unit
) {
  ChangeStatusBarColor(isDark)
  LaunchedEffect(key1 = Unit) {
    homeViewModel.getCoffees()
  }

  val coffees by homeViewModel.coffees.collectAsState()
  val uiState by homeViewModel.uiState.collectAsState()
  when (uiState) {
    HomeUiState.Loading -> HomeLoading()
    HomeUiState.Success -> HomeLayout(coffees, modifier, onCoffeeClicked)
    else -> Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Not implemented")
    }
  }
}

@Composable
fun HomeLoading(modifier: Modifier = Modifier) {
  Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    CircularProgressIndicator()
  }
}

@Preview
@Composable
private fun HomeLoadingPrev() {
  ComposePracticeApp {
    HomeLoading()
  }
}

@Composable
private fun HomeLayout(
  coffees: List<Coffee>,
  modifier: Modifier = Modifier,
  onCoffeeClicked: (Int) -> Unit
) {
  Scaffold(
    bottomBar = { BottomBarMenu() }
  ) { paddingValues ->
    Column(
      modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.tertiary)
        .padding(paddingValues)
        .background(MaterialTheme.colorScheme.onTertiary)
    ) {
      HomeHeader()
      Column(
        Modifier
          .background(MaterialTheme.colorScheme.background)
          .padding(horizontal = 24.dp)
      ) {
        CoffeeFilters()
        CoffeeList(coffees) { onCoffeeClicked(it) }
      }
    }
  }
}

@Preview
@Composable
private fun HomeLayoutPreview() {
  val coffees = (1..3).map {
    Coffee("", 5.0f, "Name", "Desc", 4.5f)
  }
  ComposePracticeApp {
    HomeLayout(coffees = coffees) {}
  }
}

@Composable
fun HomeHeader(modifier: Modifier = Modifier) {
  var headerSize by remember { mutableStateOf(IntSize.Zero) }
  var promoSize by remember { mutableStateOf(IntSize.Zero) }
  val density = LocalDensity.current

  Box(modifier.background(MaterialTheme.colorScheme.background)) {
    Box(modifier = Modifier
      .fillMaxWidth()
      .height(with(density) { (headerSize.height - promoSize.height / 2).toDp() })
      .background(MaterialTheme.colorScheme.tertiary))
    Column(
      Modifier
        .onSizeChanged { headerSize = it }
        .padding(top = 24.dp, end = 24.dp, bottom = 0.dp, start = 24.dp)) {
      Text(text = "Location", color = MaterialTheme.colorScheme.onTertiary)
      Row {
        Text(
          text = "Bogotá, Colombia",
          color = MaterialTheme.colorScheme.onTertiary,)
        Icon(
          imageVector = Icons.Default.ArrowDropDown,
          contentDescription = null,
          tint = Color.White
        )
      }
      HomeSearch()
      Promo(Modifier.onSizeChanged { promoSize = it })
    }
  }
}

@Composable
fun HomeSearch(modifier: Modifier = Modifier) {
  var searchText by remember { mutableStateOf("") }

  Row(
    modifier
      .padding(vertical = 24.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalAlignment = Alignment.CenterVertically) {
      TextField(
        value = searchText,
        modifier = Modifier
          .weight(1f)
          .clip(RoundedCornerShape(12.dp)),
        placeholder = { Text(text = "Search coffee") },
        onValueChange = { searchText = it},
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) })
      IconButtonPrimary(icon = Icons.Default.Menu, padding = 4f)
  }
}
