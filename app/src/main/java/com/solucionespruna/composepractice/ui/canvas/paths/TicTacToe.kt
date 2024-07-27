package com.solucionespruna.composepractice.ui.canvas.paths

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

private val strokeWidth = 4.dp

@Preview
@Composable
fun TicTacToe(modifier: Modifier = Modifier) {
  val positionsCount = 9
  val pathPortionsCircle by remember {
    mutableStateOf((0..<positionsCount).map { Animatable(0f) }.toList())
  }
  val pathPortionsCross by remember {
    mutableStateOf((0..<positionsCount).map { Animatable(0f) }.toList())
  }
  val scope =  rememberCoroutineScope()

  Box(modifier.safeContentPadding().padding(32.dp)) {
    TicTacToeLayout(positionsCount, pathPortionsCircle, pathPortionsCross)
    Button(onClick = {
      for (it in 0..<positionsCount) {
        scope.launch {
          async {
            pathPortionsCircle[it].animateTo(0f, tween(500))
          }
          async {
            pathPortionsCross[it].animateTo(0f, tween(500))
          }
        }
      }
    },
      Modifier
        .align(Alignment.BottomCenter)
        .fillMaxWidth()) {
      Text(text = "Reset")
    }
  }
}

@Composable
fun TicTacToeLayout(
  positionsCount: Int,
  pathPortionsCircle: List<Animatable<Float, AnimationVector1D>>,
  pathPortionsCross: List<Animatable<Float, AnimationVector1D>>,
  modifier: Modifier = Modifier
) {
  val pathState by remember {
    mutableStateOf((0..<positionsCount).map { "E" }.toMutableList())
  }
  var currentTurn by remember {
    mutableStateOf("X")
  }
  val scope = rememberCoroutineScope()

  Canvas(modifier = modifier
    .fillMaxSize()
    .pointerInput(key1 = true) {
      detectTapGestures {
        scope.launch {
          val positionClicked = getPositionFromOffset(size.width.toFloat(), it)
          if (positionClicked == -1) return@launch
          pathState[positionClicked] = currentTurn
          currentTurn = when (currentTurn) {
            "X" -> "O"
            "O" -> "X"
            else -> "E"
          }

          if (pathState[positionClicked] == "O")
            pathPortionsCircle[positionClicked].animateTo(
              1f, tween(1000)
            )
          else if (pathState[positionClicked] == "X")
            pathPortionsCross[positionClicked].animateTo(
              1f, tween(1000)
            )
        }
      }
    }
  ) {
    val gameSize = size.width
    drawVerticalLine(Offset(x= gameSize / 3, 0f), gameSize)
    drawVerticalLine(Offset(x= 2 * gameSize / 3, 0f), gameSize)
    drawHorizontalLine(Offset(0f, gameSize / 3), gameSize)
    drawHorizontalLine(Offset(0f, 2 * gameSize / 3), gameSize)
    for (it in (0..<9)) {
        drawGameCircle(it, gameSize, pathPortionsCircle[it].value)
        drawGameCross(it, gameSize, pathPortionsCross[it].value)
    }
  }
}

private fun DrawScope.drawVerticalLine(offset: Offset, length: Float) {
  drawGameLine(offset, Offset(offset.x, offset.y + length))
}

private fun DrawScope.drawHorizontalLine(offset: Offset, length: Float) {
  drawGameLine(offset, Offset(offset.x + length, offset.y))
}

private fun DrawScope.drawGameLine(offset1: Offset, offset2: Offset) {
  drawLine(Color.Black, offset1, offset2, strokeWidth = strokeWidth.toPx(), cap = StrokeCap.Round)
}

private fun DrawScope.drawGameCircle(position: Int, gameSize: Float, pathPortion: Float) {
  val center = getPositionCenter(gameSize, position)
  val radius = gameSize / 18
  val path = Path().apply {
    addOval(Rect(center, radius))
  }
  val outPath = Path()
  PathMeasure().apply {
    setPath(path, false)
    getSegment(0f, length * pathPortion, outPath, startWithMoveTo = true)
  }
  drawPath(outPath, Color.Red, style = Stroke(width = 4.dp.toPx()))
}

private fun DrawScope.drawGameCross(position: Int, gameSize: Float, pathPortion: Float) {
  val center = getPositionCenter(gameSize, position)
  val radius = gameSize / 18
  val path1 = Path().apply {
    moveTo(center.x - radius, center.y - radius)
    lineTo(center.x + radius, center.y + radius)
  }
  val path2 = Path().apply {
    moveTo(center.x - radius, center.y + radius)
    lineTo(center.x + radius, center.y - radius)
  }
//  val crossPath = Path().apply {
//    op(path1, path2, PathOperation.Union)
//  }

  val outPath = Path()
  PathMeasure().apply {
    setPath(path1, false)
    getSegment(0f, length * pathPortion, outPath, true)
  }
  val outPath2 = Path()
  PathMeasure().apply {
    setPath(path2, false)
    getSegment(0f, length * pathPortion, outPath2, true)
  }

  val style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
//  drawPath(crossPath, Color.Green, style = style)
  drawPath(outPath, Color.Green, style = style)
  drawPath(outPath2, Color.Green, style = style)
}

private fun getPositionCenter(gameSize: Float, position: Int): Offset {
  val initialOffset  = gameSize / 6
  val positionsDistance = gameSize / 3
  val xPosition = position % 3
  val yPosition = position / 3

  return Offset(
    initialOffset + positionsDistance * xPosition,
    initialOffset + positionsDistance * yPosition)
}

private fun getPositionFromOffset(gameSize: Float, offset: Offset): Int {
  if (offset.x > gameSize || offset.y > gameSize) return -1

  val positionSide = gameSize / 3
  val xPosition = (offset.x / positionSide).toInt()
  val yPosition = (offset.y / positionSide).toInt()

  return xPosition + 3 * yPosition
}

