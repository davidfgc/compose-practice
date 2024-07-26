package com.solucionespruna.composepractice.ui.canvas.paths

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun BottomBarWithShape(modifier: Modifier = Modifier) {
  val innerModifier = Modifier.padding(16.dp)
  Column {
    BottomBarWithShapeLayout(innerModifier)
    BottomBarOnePath(innerModifier)
    BottomBarThreePaths(innerModifier)
  }
}

@Composable
private fun BottomBarWithShapeLayout(
  modifier: Modifier = Modifier,
  height: Dp = 50.dp,
  radius: Dp = 40.dp,
  background: Color = Color.LightGray,
  drawStyle: DrawStyle = Fill
) {
  Canvas(
    modifier = modifier
      .fillMaxWidth()
      .height(height + radius)
  ) {
    val heightPx = height.toPx()
    val radiusPx = radius.toPx()
    val smallRadius = 10.dp.toPx()
    val controlPointDistance = 1.5f * radiusPx - smallRadius

    val path = Path().apply {
      moveTo(0f, size.height)
      lineTo(0f, size.height - heightPx + smallRadius)
      quadraticBezierTo(
        0f, size.height - heightPx,
        smallRadius, size.height - heightPx
      )
      lineTo(size.width / 2 - radiusPx - smallRadius, size.height - heightPx)
      quadraticBezierTo(
        size.width / 2 - radiusPx, size.height - heightPx,
        size.width / 2 - radiusPx, size.height - heightPx - smallRadius
      )
      cubicTo(
        size.width / 2 - radiusPx + smallRadius, size.height - heightPx - controlPointDistance,
        size.width / 2 + radiusPx - smallRadius, size.height - heightPx - controlPointDistance,
        size.width / 2 + radiusPx, size.height - heightPx - smallRadius
      )
      quadraticBezierTo(
        size.width / 2 + radiusPx, size.height - heightPx,
        size.width / 2 + radiusPx + smallRadius, size.height - heightPx
      )
      lineTo(size.width - smallRadius, size.height - heightPx)
      quadraticBezierTo(
        size.width, size.height - heightPx,
        size.width, size.height - heightPx + smallRadius,
      )
      lineTo(size.width, size.height)
      close()
    }
    drawPath(path, background, style = drawStyle)
  }
}

@Composable
fun BottomBarOnePath(
  modifier: Modifier = Modifier,
  height: Dp = 50.dp,
  radius: Dp = 40.dp,
  background: Color = Color.LightGray,
) {
  Canvas(
    modifier = modifier
      .fillMaxWidth()
      .height(height + radius)
  ) {
    val heightPx = height.toPx()
    val radiusPx = radius.toPx()

    val path = Path().apply {
      moveTo(0f, size.height)
      lineTo(0f, size.height - heightPx)
      lineTo(size.width/2 - radiusPx, size.height - heightPx)
      lineTo(size.width/2 - radiusPx/2, size.height - heightPx - radiusPx)
      lineTo(size.width/2 + radiusPx/2, size.height - heightPx - radiusPx)
      lineTo(size.width/2 + radiusPx, size.height - heightPx)
      lineTo(size.width, size.height - heightPx)
      lineTo(size.width, size.height)
//      close()
    }

    drawPath(path, background, style = Stroke(
      width = 2.dp.toPx(),
      cap = StrokeCap.Round,
      pathEffect = PathEffect.cornerPathEffect(50f)
    ))
  }
}

@Composable
fun BottomBarThreePaths(
  modifier: Modifier = Modifier,
  height: Dp = 50.dp,
  radius: Dp = 40.dp,
  background: Color = Color.LightGray,
) {
  Canvas(
    modifier = modifier
      .fillMaxWidth()
      .height(height + radius)
  ) {
    val heightPx = height.toPx()
    val radiusPx = radius.toPx()

    val pathLeftCorner = Path().apply {
      moveTo(0f, size.height)
      lineTo(0f, size.height - heightPx)
      lineTo(radiusPx, size.height - heightPx)
    }

    val pathMiddle = Path().apply {
      moveTo(radiusPx, size.height - heightPx)
      lineTo(size.width/2 - radiusPx, size.height - heightPx)
      lineTo(size.width/2 - radiusPx/2, size.height - heightPx - radiusPx)
      lineTo(size.width/2 + radiusPx/2, size.height - heightPx - radiusPx)
      lineTo(size.width/2 + radiusPx, size.height - heightPx)
      lineTo(size.width - radiusPx, size.height - heightPx)
    }
    val pathRightCorner = Path().apply {
      moveTo(size.width - radiusPx, size.height - heightPx)
      lineTo(size.width, size.height - heightPx)
      lineTo(size.width, size.height)
    }

    val style = Stroke(width = 2.dp.toPx(), pathEffect = PathEffect.cornerPathEffect(radiusPx))
    drawPath(pathLeftCorner, background, style = style)
    drawPath(pathMiddle, background, style = style)
    drawPath(pathRightCorner, background, style = style)
  }
}