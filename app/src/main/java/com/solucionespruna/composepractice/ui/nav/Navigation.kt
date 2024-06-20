package com.solucionespruna.composepractice.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.solucionespruna.composepractice.ui.coffeshop.coffeedetail.CoffeeDetail
import com.solucionespruna.composepractice.ui.coffeshop.home.HomeScreen
import com.solucionespruna.composepractice.ui.coffeshop.welcome.WelcomeScreen

@Composable
fun Navigation() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = NavItem.Welcome.baseRoute) {
    composable(NavItem.Welcome.baseRoute) {
      WelcomeScreen {
        navController.navigate(NavItem.Home.baseRoute)
      }
    }
    composable(NavItem.Home.baseRoute) {
      HomeScreen {
        navController.navigate(NavItem.CoffeeDetail.createNavRoute(it))
      }
    }
    composable(NavItem.CoffeeDetail.route, NavItem.CoffeeDetail.arguments) {backStackEntry ->
      val coffeeId = backStackEntry.arguments!!.getInt(NavArg.CoffeeId.key)
      CoffeeDetail(coffeeId)
    }
  }
}

sealed class NavItem(val baseRoute: String, val navArgs: List<NavArg> = emptyList()) {
  data object Welcome: NavItem("welcome")
  data object Home: NavItem("home")
  data object CoffeeDetail: NavItem("coffee-detail", listOf(NavArg.CoffeeId)) {
    fun createNavRoute(coffeeId: Int) = "$baseRoute/$coffeeId"
  }

  val route =
    listOf(baseRoute).plus(navArgs.map { "{${it.key}}" }).joinToString("/")
  val arguments = navArgs.map { navArgument(it.key) { type = it.type } }
}

enum class NavArg(val key: String, val type: NavType<*>) {
  CoffeeId("coffeeId", NavType.IntType)
}