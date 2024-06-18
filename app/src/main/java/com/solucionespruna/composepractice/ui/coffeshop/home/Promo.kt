package com.solucionespruna.composepractice.ui.coffeshop.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.solucionespruna.composepractice.R
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme

@Composable
fun Promo(modifier: Modifier = Modifier) {
  var boxSize by remember { mutableStateOf(IntSize.Zero) }
  val density = LocalDensity.current

  Box(
    modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(8.dp))
  ) {
    Image(
      painter = painterResource(id = R.drawable.promo_bg),
      contentDescription = null,
      modifier = Modifier.fillMaxWidth().height(with(density) { boxSize.height.toDp() + Dp(40f)}),
      contentScale = ContentScale.Crop,
    )
    Column (
      Modifier
        .padding(start = 24.dp)
        .fillMaxWidth()
        .padding(vertical = 16.dp)
        .onSizeChanged { intSize -> boxSize = intSize},
      verticalArrangement = Arrangement.SpaceEvenly) {
      Text("Promo",
        Modifier
          .padding(bottom = 8.dp)
          .clip(RoundedCornerShape(4.dp))
          .background(MaterialTheme.colorScheme.error)
          .padding(horizontal = 8.dp, vertical = 4.dp), color = Color.White, fontWeight = FontWeight.Bold)
      TextWithOffsetBackground(text = "Buy one get")
      TextWithOffsetBackground(text = "one FREE")
    }
  }
}

@Composable
fun TextWithOffsetBackground(text: String, modifier: Modifier = Modifier) {
  var textSize by remember {
    mutableStateOf(IntSize.Zero)
  }
  val density = LocalDensity.current
  
  BoxWithConstraints(modifier) {
    if (textSize != IntSize.Zero) {
      Box(modifier = Modifier
        .padding(top = 20.dp)
        .size(with(density) { (textSize.width + 16).toDp() }, with(density) { (textSize.height - 48).toDp() })
        .background(MaterialTheme.colorScheme.tertiary))
    }
    Text(
      text = text,
      color = Color.White,
      modifier = Modifier.onSizeChanged { intSize -> textSize = intSize },
      style = MaterialTheme.typography.displaySmall,
      fontWeight = FontWeight.Medium
    )
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PromoPreview() {
  ComposePracticeTheme {
    Surface {
      Promo()
    }
  }
}