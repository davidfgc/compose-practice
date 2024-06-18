package com.solucionespruna.composepractice.ui.coffeshop.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
  var headerSize by remember { mutableStateOf(IntSize.Zero) }
  var promoSize by remember { mutableStateOf(IntSize.Zero) }
  val density = LocalDensity.current

  Box(modifier.fillMaxSize()) {
    Column(Modifier.fillMaxSize()) {
      BoxWithConstraints(
        Modifier
          .fillMaxSize()
          .background(MaterialTheme.colorScheme.tertiary)
          .weight(1f)
          .onSizeChanged { headerSize = it }
      ) {
      }
      Box(modifier = Modifier
        .fillMaxSize()
        .weight(2f))
    }
    if (headerSize != IntSize.Zero) {
      BoxWithConstraints {
        Promo(
          Modifier
            .padding(horizontal = 24.dp)
            .align(Alignment.TopCenter)
            .offset(y = with(density) { (headerSize.height - promoSize.height / 2).toDp() })
            .onSizeChanged { promoSize = it }
        )
      }
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