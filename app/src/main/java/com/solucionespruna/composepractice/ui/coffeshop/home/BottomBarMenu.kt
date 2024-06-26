package com.solucionespruna.composepractice.ui.coffeshop.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme

@Composable
fun BottomBarMenu(modifier: Modifier = Modifier) {
  val (selectedIndex, setSelectedIndex) = remember { mutableIntStateOf(0) }

  Card(
    modifier = modifier.background(MaterialTheme.colorScheme.background),
    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.surface)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
        .navigationBarsPadding()
      ,
      horizontalArrangement = Arrangement.Absolute.SpaceEvenly) {
      BottomBarItem.getItems().mapIndexed { index, item ->
        val tint = when (index) {
          selectedIndex -> MaterialTheme.colorScheme.primary
          else -> MaterialTheme.colorScheme.onSurface
        }
        IconButton(onClick = { setSelectedIndex(index) }) {
          Icon(imageVector = item.icon, contentDescription = null, tint = tint)
        }
      }
    }
  }
}

@Preview
@Composable
private fun BottomBarMenuPreview() {
  ComposePracticeTheme {
    Box(modifier = Modifier.padding(16.dp)) {
      BottomBarMenu()
    }
  }
}

class BottomBarItem(val icon: ImageVector) {
  companion object {
    fun getItems(): List<BottomBarItem> = listOf(
      BottomBarItem(Icons.Default.Home),
      BottomBarItem(Icons.Default.FavoriteBorder),
      BottomBarItem(Icons.Outlined.ShoppingCart),
      BottomBarItem(Icons.Outlined.Notifications)
    )
  }
}