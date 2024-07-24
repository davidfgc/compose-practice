package com.solucionespruna.composepractice.ui.index

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solucionespruna.composepractice.ComposePracticeApp
import com.solucionespruna.composepractice.ui.nav.NavItem

@Composable
fun IndexScreen(modifier: Modifier = Modifier, navTo: (String) -> Unit) {
  Scaffold { paddingValues ->
    Column(modifier.padding(paddingValues)) {
      IndexItem("PokeDex", onClicked = { navTo(it) }, destination = NavItem.PokemonList)
      IndexItem("Coffee Shop", onClicked = { navTo(it) }, destination = NavItem.Welcome)
      IndexItem("Canvas", onClicked = { navTo(it) }, destination = NavItem.BasicShapes)
      IndexItem("Overlay", onClicked = { navTo(it) }, destination = NavItem.Overlay)
    }
  }
}

@Composable
private fun IndexItem(
  name: String, modifier: Modifier = Modifier, onClicked: (String) -> Unit, destination: NavItem) {
  Row(
    modifier
      .clickable { onClicked(destination.baseRoute) }
      .padding(16.dp)) {
    Text(text = name, Modifier.weight(1f))
    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun IndexScreenPrev() {
  ComposePracticeApp {
    IndexScreen {}
  }
}