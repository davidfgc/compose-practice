package com.solucionespruna.composepractice.ui.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@Preview(showBackground = true)
@Composable
fun BasicShapesScreen() {
  val initialTime = 60
  var timeLeft by remember {
    mutableIntStateOf(initialTime)
  }

  Column(
    Modifier.safeDrawingPadding(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Text(text = "Time Left: $timeLeft")
    Timer(Modifier.fillMaxSize()) {
      timeLeft = initialTime - it
    }
  }
}

@Composable
fun Timer(modifier: Modifier = Modifier, stepsCount: Int = 60, onProgress: (steps: Int)->Unit) {
  BoxWithConstraints {
    val radius = min(constraints.maxWidth, constraints.maxHeight) / 2f
    val angleDiff = 360.0 / stepsCount
    var steps by remember {
      mutableFloatStateOf(0f)
    }

    LaunchedEffect(key1 = steps) {
      delay(1000)
      if (steps > stepsCount - 1) steps = 0f
      else steps++
      onProgress(steps.toInt())
    }

    Canvas(modifier = modifier) {
      drawCircle(
        Color.Black,
        style = Stroke()
      )
      val archType = when {
        stepsCount - steps < 10 -> ArchType.Danger
        stepsCount - steps < stepsCount / 2 -> ArchType.Warning
        else -> ArchType.Normal
      }
      drawArc(
        color = archType.color,
        startAngle = -90f,
        sweepAngle = -steps * angleDiff.toFloat(),
        useCenter = true,
        size = Size(radius * 2, radius * 2),
        topLeft = Offset(
          center.x - radius,
          center.y - radius
        )
      )
      for (i in 1..stepsCount) {
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

sealed class ArchType(val color: Color) {
  data object Normal: ArchType(Color.Green)
  data object Warning: ArchType(Color.Yellow)
  data object Danger: ArchType(Color.Red)
}