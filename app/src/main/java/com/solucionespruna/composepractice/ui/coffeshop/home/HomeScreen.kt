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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.solucionespruna.composepractice.ui.coffeshop.common.IconAccentButton
import com.solucionespruna.composepractice.ui.theme.ChangeStatusBarColor
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier, isDark: Boolean = true) {
  ChangeStatusBarColor(isDark)

  Scaffold(
    bottomBar = { BottomBarMenu() }
  ) { padding ->
    Column(
      modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.tertiary)
        .padding(top = padding.calculateTopPadding())
        .background(MaterialTheme.colorScheme.onTertiary)
    ) {
      HomeHeader()
      Column(
        Modifier
          .background(Color.White)
          .padding(bottom = padding.calculateBottomPadding().minus(Dp(16f)))
          .background(MaterialTheme.colorScheme.onTertiary)
          .padding(horizontal = 24.dp)
      ) {
        CoffeeFilters()
        CoffeeList(Modifier.padding(bottom = 24.dp))
      }
    }
  }
}

@Composable
fun HomeHeader(modifier: Modifier = Modifier) {
  var headerSize by remember { mutableStateOf(IntSize.Zero) }
  var promoSize by remember { mutableStateOf(IntSize.Zero) }
  val density = LocalDensity.current

  Box(modifier.background(MaterialTheme.colorScheme.onTertiary)) {
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
      IconAccentButton(icon = Icons.Default.Menu, padding = 4f)
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
  ComposePracticeTheme {
    Surface {
      HomeScreen()
    }
  }
}