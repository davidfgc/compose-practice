package com.solucionespruna.composepractice.ui.canvas.paths

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeaderScreen(modifier: Modifier = Modifier) {
  Canvas(modifier = modifier.fillMaxSize()) {
    val height = 200.dp.toPx()
    val path = Path().apply {
      moveTo(0f, 0f)
      lineTo(0f, height)
      quadraticBezierTo(size.width / 2, height * 1.5f, size.width, height)
      lineTo(size.width, 0f)
      close()
    }
    drawPath(path = path, Color.Blue)
  }
}