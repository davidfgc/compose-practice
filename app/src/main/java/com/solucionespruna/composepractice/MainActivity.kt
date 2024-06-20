package com.solucionespruna.composepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.solucionespruna.composepractice.ui.coffeshop.home.HomeScreen
import com.solucionespruna.composepractice.ui.coffeshop.welcome.WelcomeScreen
import com.solucionespruna.composepractice.ui.nav.Navigation
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ComposePracticeApp {
        Navigation()
      }
    }
  }
}

@Composable
private fun ComposePracticeApp(content: @Composable () -> Unit) {
  ComposePracticeTheme {
    Surface {
      content()
    }
  }
}
