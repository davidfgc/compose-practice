package com.solucionespruna.composepractice.ui.canvas.paths

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.solucionespruna.composepractice.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuadraticBezierHeader(modifier: Modifier = Modifier) {
  val headerColor = Color.Blue
  val contentColor = Color.Green
  val headerHeight = 200.dp

  Column(
    modifier
      .background(contentColor)
      .fillMaxSize()) {
    CurveHeader(modifier = Modifier, headerHeight = headerHeight, headerColor = headerColor)
    Spacer(modifier = Modifier.height(headerHeight))
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
      ImageHeader()
      Content()
    }
  }
}

@Composable
private fun CurveHeader(
  modifier: Modifier,
  headerHeight: Dp,
  headerColor: Color
) {
  Canvas(
    modifier = modifier
      .fillMaxWidth()
      .wrapContentHeight()
  ) {
    val height = headerHeight.toPx()
    val path = Path().apply {
      moveTo(0f, 0f)
      lineTo(0f, height)
      quadraticBezierTo(size.width / 2, height * 1.5f, size.width, height)
      lineTo(size.width, 0f)
      close()
    }
    drawPath(path = path, headerColor)
  }
}

@Composable
private fun ImageHeader(onSizeChanged: (IntSize) -> Unit = {}) {
  Image(
    painter = painterResource(id = R.drawable.ic_launcher_foreground),
    contentDescription = null,
    Modifier
      .background(
        Brush.radialGradient(colors = listOf(Color.Black, Color.White)),
        shape = CircleShape,
        alpha = 0.5f
      )
      .onSizeChanged { onSizeChanged(it) }
  )
}

@Composable
private fun Content() {
  Text(
    text = "Here starts the content",
    color = Color.White,
    fontWeight = FontWeight.ExtraBold,
    style = MaterialTheme.typography.titleLarge
  )
}
