package com.solucionespruna.composepractice.ui.canvas.paths

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AnimatedPath(modifier: Modifier = Modifier) {
  val pathPortion = remember {
    Animatable(0f)
  }
  LaunchedEffect(key1 = true) {
    pathPortion.animateTo(
      targetValue = 1f,
      animationSpec = tween(
        3000, easing = LinearEasing
      )
    )
  }

  Canvas(modifier = modifier
    .fillMaxSize()
    .padding(16.dp)) {
    val controlPointsDistance = size.width / 2 * 1.32f
    val point1 = Offset(size.width, size.height/2)
    val point2 = Offset(0f, size.height/2)
    val path = Path().apply {
      moveTo(point1.x, point1.y)
      cubicTo(
        point1.x, point1.y - controlPointsDistance,
        point2.x, point2.y - controlPointsDistance,
        point2.x, point2.y
      )
      cubicTo(
        point2.x, point2.y + controlPointsDistance,
        point1.x, point2.y + controlPointsDistance,
        point1.x, point1.y,
      )
    }
//    drawPath(path, Color.Red, style =  Stroke(width = 4.dp.toPx()))

    val radius = 500f
    val path2 = Path().apply {
      moveTo(center.x, center.y - radius)
      lineTo(center.x - radius, center.y)
      lineTo(center.x, center.y + radius)
      lineTo(center.x + radius, center.y)
      close()
    }
//    drawPath(path2, Color.Green, style = Stroke(width = 2.dp.toPx(), pathEffect = PathEffect.cornerPathEffect(400f)))

    val path3 = Path().apply {
      addOval(Rect(center, size.width/2))
    }
    val outPath = Path()
    PathMeasure().apply {
      setPath(path3, false)
      getSegment(0f, length * pathPortion.value, outPath, true)
    }
    rotate(-90f) {
      drawPath(outPath, Color.Blue, style = Stroke(width = 10.dp.toPx(), cap = StrokeCap.Round))
    }
  }
}