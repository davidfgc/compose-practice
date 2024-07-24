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
fun CubicHeader() {
  CurvedHeader()
}

@Composable
fun CurvedHeader(modifer: Modifier = Modifier) {
  val headerColor = Color.Blue
  val headerHeight1 = 150.dp
  val headerHeight2 = 250.dp
  val controlPointDistance = 300f

  Canvas(modifier = modifer.fillMaxSize()) {
    val height1 = headerHeight1.toPx()
    val height2 = headerHeight2.toPx()
    val path = Path().apply {
      moveTo(0f, 0f)
      lineTo(0f, height1)
      cubicTo(
        0f, height1 + controlPointDistance,
        size.width, height1,
        size.width, height2)
      lineTo(size.width, 0f)
      close()
    }
    drawPath(path, headerColor)
  }
}
