package com.solucionespruna.composepractice.ui.ovelay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.solucionespruna.composepractice.ComposePracticeApp
import com.solucionespruna.composepractice.ui.common.NavigationIcon

@Composable
fun WithBoxes(modifier: Modifier = Modifier, upNavigation: () -> Unit) {
  var headerSize by remember { mutableStateOf(IntSize.Zero) }
  var overlaySize by remember { mutableStateOf(IntSize.Zero) }
  val headerBackgroundHeight = with(LocalDensity.current) {
    (headerSize.height - overlaySize.height / 2).toDp()
  }

  Column(modifier.fillMaxSize()) {
    Box(
      Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background),
      contentAlignment = Alignment.TopCenter) {
      Box(
        Modifier
          .fillMaxWidth()
          .background(MaterialTheme.colorScheme.primary)
          .statusBarsPadding()
          .height(headerBackgroundHeight)) {
        NavigationIcon(upNavigation)
      }
      Column(
        Modifier
          .statusBarsPadding()
          .padding(bottom = 4.dp)
          .onSizeChanged { headerSize = it },
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text("Header", Modifier.padding(16.dp), color = MaterialTheme.colorScheme.onPrimary)
        Card(
          Modifier.onSizeChanged { overlaySize = it },
          elevation = CardDefaults.cardElevation(4.dp)) {
          Column(
            Modifier
              .background(MaterialTheme.colorScheme.background)
              .padding(16.dp)) {
            Text(text = "Overlay With Boxes")
          }
        }
      }
    }
    Column(
      Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(16.dp)
    ) {
      Text(text = "Content")
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WithBoxesPreview() {
  ComposePracticeApp {
    WithBoxes {}
  }
}