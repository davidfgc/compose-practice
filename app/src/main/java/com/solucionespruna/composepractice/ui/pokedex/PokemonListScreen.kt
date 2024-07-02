package com.solucionespruna.composepractice.ui.pokedex

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solucionespruna.composepractice.ComposePracticeApp
import com.solucionespruna.composepractice.R
import com.solucionespruna.composepractice.data.pokedex.Pokemon
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PokemonListScreen(viewModel: PokemonListViewModel = viewModel()) {
  LaunchedEffect(key1 = Unit) {
    viewModel.getPokemonList()
  }
  val pokemonList by viewModel.pokemonList.collectAsState()

  PokemonListLayout(pokemonList = pokemonList)
}

@Composable
fun PokemonListLayout(pokemonList: List<Pokemon>, modifier: Modifier = Modifier) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(pokemonList.count()) {
      PokemonCard(pokemon = Pokemon(pokemonList[it].name, ""), color = MaterialTheme.colorScheme.error)
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PokemonListLayoutPreview() {
  val pokemonList = (1..10).map {
    Pokemon("name $it", "url")
  }

  ComposePracticeApp {
    PokemonListLayout(pokemonList)
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonCard(pokemon: Pokemon, color: Color, modifier: Modifier = Modifier) {
  Card(onClick = { /*TODO*/ }, modifier = modifier) {
    Column(Modifier.background(color)) {
      Text(text = "#001",
        Modifier
          .fillMaxWidth()
          .padding(top = 8.dp, end = 8.dp), textAlign = TextAlign.End)
      Text(
        text = pokemon.name.capitalize(Locale.current),
        modifier = Modifier.padding(start = 12.dp),
        color = MaterialTheme.colorScheme.background,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold
      )
      Box(Modifier.fillMaxWidth()) {
        Column(
          Modifier
            .padding(start = 12.dp)
            .padding(vertical = 12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
          PokeDexTag("Grass", color)
          PokeDexTag("Poison", color)
        }
        Image(
          painter = painterResource(id = R.drawable.ic_launcher_foreground),
          contentDescription = null,
          Modifier
            .align(Alignment.BottomEnd)
            .size(85.dp)
        )
      }
    }
  }
}

@Composable
fun PokeDexTag(text: String, color: Color, modifier: Modifier = Modifier) {
  Box(
    modifier
      .clip(RoundedCornerShape(16.dp))
      .background(Color.White)
      .background(color.copy(alpha = 0.5f))
      .alpha(1f)
      .padding(horizontal = 20.dp, vertical = 6.dp)
  ) {
    Text(text = text, color = MaterialTheme.colorScheme.background)
  }
}

@Preview
@Composable
private fun PokeDexTagPreview() {
  ComposePracticeApp {
    PokeDexTag(text = "Grass", color = Color.Red)
  }
}