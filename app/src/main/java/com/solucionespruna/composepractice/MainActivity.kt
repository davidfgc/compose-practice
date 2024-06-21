package com.solucionespruna.composepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.solucionespruna.composepractice.ui.nav.Navigation
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
fun ComposePracticeApp(content: @Composable () -> Unit) {
  ComposePracticeTheme {
    Surface {
      content()
    }
  }
}
