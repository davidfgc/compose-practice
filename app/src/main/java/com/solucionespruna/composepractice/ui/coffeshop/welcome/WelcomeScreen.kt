package com.solucionespruna.composepractice.ui.coffeshop.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solucionespruna.composepractice.R
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme

@Composable
fun WelcomeScreen(
  modifier: Modifier = Modifier,
  onGetStartedClicked: () -> Unit
) {
  Scaffold { paddingValues ->
    Box (
      modifier
        .background(Color.Black)
        .fillMaxSize()
        .padding(paddingValues)
        .padding(horizontal = 24.dp)) {
      Image(
        painter = painterResource(id = R.drawable.welcome_bg),
        contentDescription = null,
        modifier = modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
      )
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .align(Alignment.BottomCenter),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
          text = "Fall in Love with Coffee in Blissful Delight!",
          color = Color.White,
          textAlign = TextAlign.Center,
          modifier = Modifier.fillMaxWidth(),
          fontWeight = FontWeight.SemiBold,
          style = MaterialTheme.typography.displaySmall
        )
        Text(
          text = "Welcome to our cozy coffee corner, where every cup is a delightful for you.",
          color = MaterialTheme.colorScheme.onTertiary,
          textAlign = TextAlign.Center,
          modifier = Modifier.fillMaxWidth()
        )
        Button(
          onClick = onGetStartedClicked,
          modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.primary)
        ) {
          Text("Get Started")
        }
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WelcomeScreenPreview() {
  ComposePracticeTheme {
    Surface {
      WelcomeScreen {}
    }
  }
}