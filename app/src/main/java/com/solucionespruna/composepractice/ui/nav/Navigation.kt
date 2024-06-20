package com.solucionespruna.composepractice.ui.nav

import androidx.annotation.NavigationRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.solucionespruna.composepractice.ui.coffeshop.home.HomeScreen
import com.solucionespruna.composepractice.ui.coffeshop.welcome.WelcomeScreen

@Composable
fun Navigation() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = "welcome") {
    composable("welcome") {
      WelcomeScreen {
        navController.navigate("home")
      }
    }
    composable("home") {
      HomeScreen()
    }
  }
}