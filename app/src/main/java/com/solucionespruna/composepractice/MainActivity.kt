package com.solucionespruna.composepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.solucionespruna.composepractice.ui.coffeshop.home.HomeScreen
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ComposePracticeTheme {
//          WelcomeScreen(Modifier.padding(innerPadding))
          HomeScreen()
      }
    }
  }
}
