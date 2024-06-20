package com.solucionespruna.composepractice.ui.coffeshop.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme

@Composable
fun CoffeeFilters(modifier: Modifier = Modifier) {
  val filters by remember { mutableStateOf(listOf("All Coffee", "Machiato", "Latte", "Americano")) }
  val (selectedIndex, setSelectedIndex) = remember { mutableIntStateOf(0) }

  Row(
    modifier
      .horizontalScroll(rememberScrollState())
      .padding(top = 24.dp, bottom = 8.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    filters.mapIndexed { index, filter ->
      CoffeeFilter(text = filter, isSelected = index == selectedIndex) {
        setSelectedIndex(index)
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
      isSelected -> Pair(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimary)
      else -> Pair(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.onSurface)
    }

    Text(
      text = text,
      modifier
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
