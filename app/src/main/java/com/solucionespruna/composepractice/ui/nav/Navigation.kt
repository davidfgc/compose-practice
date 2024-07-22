package com.solucionespruna.composepractice.ui.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.solucionespruna.composepractice.ui.canvas.BasicShapesScreen
import com.solucionespruna.composepractice.ui.coffeshop.coffeedetail.CoffeeDetail
import com.solucionespruna.composepractice.ui.coffeshop.home.HomeScreen
import com.solucionespruna.composepractice.ui.coffeshop.home.HomeViewModel
import com.solucionespruna.composepractice.ui.coffeshop.welcome.WelcomeScreen
import com.solucionespruna.composepractice.ui.index.IndexScreen
import com.solucionespruna.composepractice.ui.ovelay.WithBoxes
import com.solucionespruna.composepractice.ui.pokedex.pokemon.PokemonScreen
import com.solucionespruna.composepractice.ui.pokedex.pokemonlist.PokemonListScreen

@Composable
fun Navigation() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = NavItem.Index.route) {
    composable(NavItem.Index.route) {
      IndexScreen { navController.navigate(it) }
    }

    composable(NavItem.PokemonList.route) {
      PokemonListScreen {
        navController.navigate("${NavItem.PokemonDetail.route}/$it")
      }
    }
    composable("${NavItem.PokemonDetail.route}/{id}") { backStackEntry ->
      val id = backStackEntry.arguments!!.getString("id")!!.toInt()
      PokemonScreen(id)
    }

    composable(NavItem.Welcome.route) {
      WelcomeScreen {
        navController.navigate(NavItem.Home.route) {
          popUpTo(NavItem.Welcome.route) {
            inclusive = true
          }
        }
      }
    }
    composable(NavItem.Home.route) {
      val homeViewModel = hiltViewModel<HomeViewModel>()
      HomeScreen(homeViewModel = homeViewModel) {
        navController.navigate(NavItem.CoffeeDetail.createNavRoute(it))
      }
    }
    composable(NavItem.CoffeeDetail.route, NavItem.CoffeeDetail.arguments) { backStackEntry ->
      val coffeeId = backStackEntry.arguments!!.getInt(NavArg.CoffeeId.key)
      CoffeeDetail(
        coffeeId,
        onUpClick = { navController.popBackStack() }
      )
    }

    composable(NavItem.BasicShapes.route) {
      BasicShapesScreen()
    }

    composable(NavItem.Overlay.route) {
      WithBoxes {
        navController.popBackStack()
      }
    }
  }
}

sealed class NavItem(val baseRoute: String, navArgs: List<NavArg> = emptyList()) {
  data object Index: NavItem("index")

  data object PokemonList: NavItem("pokemon-list")
  data object PokemonDetail: NavItem("pokemon-detail")

  data object Welcome: NavItem("welcome")
  data object Home: NavItem("home")
  data object CoffeeDetail: NavItem("coffee-detail", listOf(NavArg.CoffeeId)) {
    fun createNavRoute(coffeeId: Int) = "$baseRoute/$coffeeId"
  }

  data object BasicShapes: NavItem("basic-shapes")

  data object Overlay: NavItem("overlay")

  val route = listOf(baseRoute).plus(navArgs.map { "{${it.key}}" }).joinToString("/")
  val arguments = navArgs.map { navArgument(it.key) { type = it.type } }
}

enum class NavArg(val key: String, val type: NavType<*>) {
  CoffeeId("coffeeId", NavType.IntType)
}