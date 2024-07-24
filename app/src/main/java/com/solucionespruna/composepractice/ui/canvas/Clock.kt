package com.solucionespruna.composepractice.ui.canvas

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.withRotation
import kotlinx.coroutines.delay
import java.util.Calendar
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ClockScreen(modifier: Modifier = Modifier) {
  val calendar = Calendar.getInstance()
  var hour by remember {
    mutableIntStateOf(calendar.get(Calendar.HOUR_OF_DAY))
  }
  var minute by remember {
    mutableIntStateOf(calendar.get(Calendar.MINUTE))
  }
  var second by remember {
    mutableIntStateOf(calendar.get(Calendar.SECOND))
  }
  LaunchedEffect(key1 = second) {
    delay(1000)
    hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    minute = Calendar.getInstance().get(Calendar.MINUTE)
    second = Calendar.getInstance().get(Calendar.SECOND)
  }

  ClockLayout(hour, minute, second, modifier)
}

@Composable
fun ClockLayout(hour: Int, minute: Int, second: Int, modifier: Modifier = Modifier) {
  val markersCount = 12
  val clockColor = Color.Black

  BoxWithConstraints(modifier.padding(16.dp)) {
    val radius = min(constraints.maxWidth, constraints.maxHeight) / 2
    val markerLength = radius / 10f
    val innerRadius = radius - markerLength

    Canvas(modifier = Modifier.fillMaxSize()) {
      rotate(-90f, center) {
        drawCircle(clockColor, style = Stroke(width = 2.dp.toPx()))
        drawClockLine(hour, 12, radius - 5 * markerLength, clockColor)
        drawClockLine(minute, 60, radius - 3 * markerLength, clockColor)
        drawClockLine(second, 60, radius - 3 * markerLength, Color.Red)
        drawCircle(clockColor, radius = radius / 10f)
        drawMarkers(markersCount, radius, innerRadius, clockColor)
      }
    }
  }
}

private fun DrawScope.drawClockLine(
  step: Int,
  stepCount: Int,
  length: Float,
  color: Color
) {
  val angle = step * 360f / stepCount
  val angleRad = angle * PI.toFloat() / 180
  val end = Offset(
    length * cos(angleRad) + center.x,
    length * sin(angleRad) + center.y,
  )
  drawLine(color, start = center, end = end, strokeWidth = 28f)
}

private fun DrawScope.drawMarkers(
  markersCount: Int,
  radius: Int,
  innerRadius: Float,
  clockColor: Color
) {
  val textSize = 120f
  val textRadius = radius - textSize
  for (i in 0..<markersCount) {
    if (i % 3 == 0) {
      drawNumber(i, markersCount, textRadius, textSize)
    } else {
      drawMarker(i, markersCount, radius, innerRadius, clockColor)
    }
  }
}

private fun DrawScope.drawNumber(
  number: Int,
  markersCount: Int,
  textRadius: Float,
  textSize: Float
) {
  val angle = number * 360f / markersCount
  val angleRad = angle * PI.toFloat() / 180
  val end = Offset(
    textRadius * cos(angleRad) + center.x,
    textRadius * sin(angleRad) + center.y
  )
  drawContext.canvas.nativeCanvas.apply {
    // TODO: rotate numbers to normal position, to avoid confusing 6 and 9
    withRotation(degrees = angle + 90, pivotX = end.x, pivotY = end.y) {
      val value = if (number == 0) "12" else number.toString()
      drawText(
        value,
        end.x,
        end.y,
        Paint().apply {
          color = android.graphics.Color.BLACK
          this.textSize = textSize
          textAlign = Paint.Align.CENTER
        }
      )
    }
  }
}

private fun DrawScope.drawMarker(
  i: Int,
  markersCount: Int,
  radius: Int,
  innerRadius: Float,
  clockColor: Color
) {
  val angle = i * 360f / markersCount
  val angleRad = angle * PI.toFloat() / 180
  val start = Offset(
    radius * cos(angleRad) + center.x,
    radius * sin(angleRad) + center.y
  )
  val end = Offset(
    innerRadius * cos(angleRad) + center.x,
    innerRadius * sin(angleRad) + center.y
  )
  drawLine(clockColor, start, end, strokeWidth = 2.dp.toPx())
}
