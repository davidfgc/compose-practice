package com.solucionespruna.composepractice.ui.canvas.basicshapes

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@Preview
@Composable
fun TimerScreen(stepsCount: Int = 60) {
  var steps by remember {
    mutableIntStateOf(0)
  }
  val archType = when {
    stepsCount - steps < 10 ->
      if (steps % 2 == 0) ArchType.Normal
      else ArchType.Danger
    stepsCount - steps < 20 -> ArchType.Warning
    else -> ArchType.Normal
  }
  var state by remember {
    mutableStateOf<TimerState>(TimerState.RESUMED)
  }
  LaunchedEffect(key1 = steps, key2 = state) {
    if (state is TimerState.PAUSED) return@LaunchedEffect

    delay(1000)
    if (steps > stepsCount - 1) steps = 0
    else steps++
  }

  ComposePracticeTheme(darkTheme = true) {
    Surface(onClick = { /*TODO*/ }) {
      Column(
        Modifier
          .background(color = archType.background)
          .safeDrawingPadding()
          .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        BoxWithConstraints(Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
          val height = with(LocalDensity.current) {
            constraints.maxWidth.toDp()
          }
          Timer(
            stepsCount,
            Modifier
              .size(constraints.maxWidth.dp, height),
            steps,
            archType = archType,
          )
          Text(
            text = "${stepsCount  - steps}",
            color = archType.color,
            fontSize = 140.sp)
        }
        TimerButton(text = state.buttonText, Modifier.fillMaxWidth(), contentColor = archType.color) {
          state = when (state) {
            is TimerState.PAUSED -> TimerState.RESUMED
            is TimerState.RESUMED -> TimerState.PAUSED
          }
        }
        TimerButton(text = "RESET", Modifier.fillMaxSize(), contentColor = archType.color) {
          steps = 0
          state = TimerState.RESUMED
        }
      }

    }
  }
}

@Composable
fun Timer(
  stepsCount: Int,
  modifier: Modifier = Modifier,
  steps: Int = 0,
  archType: ArchType = ArchType.Normal
) {
  BoxWithConstraints {
    val radius = min(constraints.maxWidth, constraints.maxHeight) / 2f
    val angleDiff = 360.0 / stepsCount

    Canvas(modifier = modifier) {
      drawCircle(
        archType.background,
        style = Stroke()
      )
      drawArc(
        color = archType.color,
        startAngle = -90f,
        sweepAngle = -steps * angleDiff.toFloat(),
        useCenter = true,
        size = Size(radius * 2, radius * 2),
        topLeft = Offset(
          center.x - radius,
          center.y - radius
        ),
      )
      drawCircle(
        archType.background,
        radius = radius - MarkerType.Normal.length
      )
      for (i in 1..stepsCount) {
        val angle = -(i * angleDiff) - 90
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

@Composable
fun TimerButton(text: String, modifier: Modifier = Modifier, contentColor: Color = Color.LightGray, onClick: ()->Unit) {
  Button(
    onClick = onClick,
    modifier,
    colors = ButtonDefaults.buttonColors(
      containerColor = Color.Transparent,
      contentColor = contentColor),
    shape = RoundedCornerShape(10.dp),
    border = BorderStroke(2.dp, contentColor)
  ) {
    Text(text = text, style = MaterialTheme.typography.displayLarge)
  }
}

sealed class TimerState(val buttonText: String) {
  data object PAUSED: TimerState("RESUME")
  data object RESUMED: TimerState("PAUSE")
}

sealed class MarkerType(val length: Float, val color: Color) {
  data object Highlighted: MarkerType(50f, Color.Red)
  data object Normal: MarkerType(40f, Color.LightGray)
}

sealed class ArchType(val background: Color, val color: Color = Color.LightGray) {
  data object Normal: ArchType(Color.Black)
  data object Warning: ArchType(Color(0xFFE4A26E), Color.White)
  data object Danger: ArchType(Color(0xFFDC3B61))
}