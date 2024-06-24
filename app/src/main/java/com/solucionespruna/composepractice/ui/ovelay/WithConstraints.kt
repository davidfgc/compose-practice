package com.solucionespruna.composepractice.ui.ovelay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.solucionespruna.composepractice.ComposePracticeApp
import com.solucionespruna.composepractice.ui.common.NavigationIcon

@Composable
fun WithConstraints(modifier: Modifier = Modifier, upNavigation: () -> Unit) {
  ConstraintLayout(
    Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)) {
    val (headerContent, headerBackground, overlay, bottomText, spacer) = createRefs()

    Box(
      modifier
        .constrainAs(headerBackground) {
          top.linkTo(parent.top)
          bottom.linkTo(spacer.bottom)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
          height = Dimension.fillToConstraints
          width = Dimension.fillToConstraints
        }
        .background(MaterialTheme.colorScheme.primary)
        .statusBarsPadding()
    ) {
      NavigationIcon(onUpClick = upNavigation)
    }
    Spacer(
      Modifier
        .background(Color.Blue)
        .constrainAs(spacer) {
          top.linkTo(overlay.top)
          bottom.linkTo(overlay.bottom)
        }
    )
    Column(
      Modifier
        .statusBarsPadding()
        .constrainAs(headerContent) {
          top.linkTo(parent.top)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
        }) {
      Text(text = "Header", Modifier.padding(16.dp), color = MaterialTheme.colorScheme.onPrimary)
    }
    Card(
      Modifier.constrainAs(overlay) {
        top.linkTo(headerContent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
      },
      elevation = CardDefaults.cardElevation(4.dp)) {
      Column(
        Modifier
          .background(MaterialTheme.colorScheme.background)
          .padding(16.dp)) {
        Text(text = "Overlay With Constraints")
      }
    }
    Column(
      Modifier
        .fillMaxSize()
        .constrainAs(bottomText) {
          top.linkTo(overlay.bottom)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
        }) {
      Text(text = "Content", Modifier.padding(16.dp))
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WithConstraintsPreview() {
  ComposePracticeApp {
    WithConstraints {}
  }
}