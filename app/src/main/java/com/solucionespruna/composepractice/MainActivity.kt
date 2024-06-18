package com.solucionespruna.composepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.solucionespruna.composepractice.ui.coffeshop.home.HomeScreen
import com.solucionespruna.composepractice.ui.coffeshop.welcome.WelcomeScreen
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ComposePracticeTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//          WelcomeScreen(Modifier.padding(innerPadding))
          HomeScreen(Modifier.padding(innerPadding))
        }
      }
    }
  }
}
