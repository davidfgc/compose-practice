package com.solucionespruna.composepractice.ui.imagereveal

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.solucionespruna.composepractice.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImageReveal(modifier: Modifier = Modifier) {
  ImageRevealLayout()
}

@Composable
fun ImageRevealLayout(modifier: Modifier = Modifier) {
  val falcaoBitmap = ImageBitmap.imageResource(id = R.drawable.falcao)
  val radius = 200f
  var revealCenter by remember {
    mutableStateOf(Offset(radius, radius))
  }

  BoxWithConstraints(modifier.fillMaxSize()) {
    val width = constraints.maxWidth
    val height = falcaoBitmap.height * width / falcaoBitmap.width

    Canvas(
      modifier = Modifier
        .fillMaxSize()
        .pointerInput(true) {
          detectDragGestures { change, dragAmount ->
            val current = Rect(revealCenter, radius)
            val limit = Rect(
              Offset(radius, radius),
              Size(
                width.toFloat() - 2 * radius,
                height.toFloat() - 2 * radius
              )
            )
            val newRevealCenter = Offset(
              revealCenter.x + dragAmount.x,
              revealCenter.y + dragAmount.y
            )
            if (current.contains(change.position)
              && limit.contains(newRevealCenter)
            ) {
              revealCenter = newRevealCenter
            }
          }
        }
    ) {
      drawImage(
        falcaoBitmap,
        dstOffset = IntOffset.Zero,
        dstSize = IntSize(width, height),
        blendMode = BlendMode.Difference
      )
      drawCircle(
        Color.White,
        radius,
        revealCenter,
        blendMode = BlendMode.Difference
      )
    }
  }
}