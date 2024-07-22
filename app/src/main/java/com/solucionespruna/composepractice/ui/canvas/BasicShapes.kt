package com.solucionespruna.composepractice.ui.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@Preview(showBackground = true)
@Composable
fun BasicShapesScreen() {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Timer(Modifier.fillMaxSize())
  }
}

@Composable
fun Timer(modifier: Modifier = Modifier) {
  BoxWithConstraints {
    val radius = min(constraints.maxWidth, constraints.maxHeight) / 2f
    val markersCount = 60
    val angleDiff = 360.0 / markersCount
    val seconds = 3f

    Canvas(modifier = modifier) {
      drawCircle(
        Color.Black,
        style = Stroke()
      )
      drawArc(
        color = Color.Cyan,
        startAngle = -90f,
        sweepAngle = -seconds * angleDiff.toFloat(),
        useCenter = true,
        size = Size(radius * 2, radius * 2),
        topLeft = Offset(
          center.x - radius,
          center.y - radius
        )
      )
      for (i in 1..markersCount) {
        val angle = (i * angleDiff) - 90
        val angleRad = (angle * PI / 180).toFloat()
        val markerType = when {
          i % 10 == 0 -> MarkerType.Highlighted
          else -> MarkerType.Normal
        }
        val start = Offset(
          center.x + (radius - markerType.length) * cos(angleRad),
          center.y + (radius - markerType.length) * sin(angleRad),
        )
        val end = Offset(
          center.x + radius * cos(angleRad),
          center.y + radius * sin(angleRad),
        )
        drawLine(
          color = markerType.color,
          start = start,
          end = end,
          strokeWidth = 4f
        )
      }
    }
  }
}

sealed class MarkerType(val length: Float, val color: Color) {
  data object Highlighted: MarkerType(50f, Color.Red)
  data object Normal: MarkerType(40f, Color.Black)
}