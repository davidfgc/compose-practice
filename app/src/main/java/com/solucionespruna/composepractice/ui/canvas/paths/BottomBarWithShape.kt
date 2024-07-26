package com.solucionespruna.composepractice.ui.canvas.paths

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BottomBarWithShape(modifier: Modifier = Modifier) {
  BottomBarWithShapeLayout(modifier)
}

@Preview
@Composable
private fun BottomBarWithShapeLayout(
  modifier: Modifier = Modifier,
  height: Dp = 50.dp,
  radius: Dp = 20.dp,
  background: Color = Color.LightGray,
  drawStyle: DrawStyle = Fill
) {
  Canvas(modifier = modifier.fillMaxSize()) {
    val heightPx = height.toPx()
    val radiusPx = radius.toPx()
    val controlPointDistance = radiusPx * 2

    val smallRadius = 10.dp.toPx()

    val path = Path().apply {
      moveTo(0f, size.height)
      lineTo(0f, size.height - heightPx + smallRadius)
      cubicTo(
        0f, size.height - heightPx,
        smallRadius, size.height - heightPx,
        smallRadius, size.height - heightPx,
      )
      lineTo(size.width / 2 - radiusPx, size.height - heightPx)
      cubicTo(
        size.width / 2 - radiusPx, size.height - heightPx - controlPointDistance,
        size.width / 2 + 2 * radiusPx, size.height - heightPx - controlPointDistance,
        size.width / 2 + 2 * radiusPx, size.height - heightPx
      )
      lineTo(size.width - smallRadius, size.height - heightPx)
      cubicTo(
        size.width - smallRadius, size.height - heightPx,
        size.width, size.height - heightPx,
        size.width, size.height - heightPx + smallRadius,
      )
      lineTo(size.width, size.height)
      close()
    }
    drawPath(path, background, style = drawStyle)
  }
}