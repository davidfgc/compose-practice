package com.solucionespruna.composepractice.ui.coffeshop.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.solucionespruna.composepractice.ui.theme.ChangeStatusBarColor
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier, isDark: Boolean = true) {
  ChangeStatusBarColor(isDark)
  Column(
    modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.tertiary)
      .padding(
        top = WindowInsets.safeContent
          .asPaddingValues()
          .calculateTopPadding()
      )) {
    HomeHeader()
    Column(
      Modifier
        .weight(1f)
        .background(Color.White)
        .padding(horizontal = 24.dp)) {
      CoffeeFilters()
    }
  }
}

@Composable
fun HomeHeader(modifier: Modifier = Modifier) {
  var headerSize by remember { mutableStateOf(IntSize.Zero) }
  var promoSize by remember { mutableStateOf(IntSize.Zero) }
  val density = LocalDensity.current

  Box(modifier.background(Color.White)) {
    Box(modifier = Modifier
      .fillMaxWidth()
      .height(with(density) { (headerSize.height - promoSize.height / 2).toDp() })
      .background(MaterialTheme.colorScheme.tertiary))
    Column(
      Modifier
        .onSizeChanged { headerSize = it }
        .padding(top = 24.dp, end = 24.dp, bottom = 0.dp, start = 24.dp)) {
      Text(text = "Location", color = MaterialTheme.colorScheme.onTertiary)
      Text(text = "Bogotá, Colombia", color = MaterialTheme.colorScheme.onTertiary)
      HomeSearch()
      Promo(Modifier.onSizeChanged { promoSize = it })
    }
  }
}

@Composable
fun HomeSearch(modifier: Modifier = Modifier) {
  var searchText by remember {
    mutableStateOf("")
  }

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
      Box(
        Modifier
          .clip(RoundedCornerShape(16.dp))
          .background(MaterialTheme.colorScheme.primary)
          .padding(16.dp)) {
          Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = null,
            tint = Color.White)
      }
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