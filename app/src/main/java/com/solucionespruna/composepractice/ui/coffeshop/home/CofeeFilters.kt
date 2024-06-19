package com.solucionespruna.composepractice.ui.coffeshop.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme

@Composable
fun CoffeeFilters(modifier: Modifier = Modifier) {
  val filters by remember { mutableStateOf(listOf("All Coffee", "Machiato", "Latte", "Americano")) }
  var selectedFilterIndex by remember { mutableIntStateOf(0) }

  LazyRow(modifier.padding(top = 24.dp, bottom = 16.dp)) {
    items(filters.count()) {
      CoffeeFilter(text = filters[it], isSelected = it == selectedFilterIndex) {
        selectedFilterIndex = it
      }
    }
  }
}

@Preview
@Composable
private fun CoffeeFiltersPreview() {
  ComposePracticeTheme {
    CoffeeFilters()
  }
}

@Composable
fun CoffeeFilter(
  text: String,
  isSelected: Boolean,
  modifier: Modifier = Modifier,
  onClick: ()->Unit) {
    val (bgColor, textColor) = when {
      isSelected -> Pair(MaterialTheme.colorScheme.primary, Color.White)
      else -> Pair(MaterialTheme.colorScheme.onTertiary, MaterialTheme.colorScheme.tertiary)
    }

    Text(
      text = text,
      modifier
        .padding(end = 16.dp)
        .clickable { onClick() }
        .clip(RoundedCornerShape(4.dp))
        .background(bgColor)
        .padding(horizontal = 8.dp, vertical = 4.dp),
      color = textColor
    )
}

@Preview
@Composable
private fun CoffeeFilterPreview() {
  ComposePracticeTheme {
    CoffeeFilter("All Coffee", true) {}
  }
}
