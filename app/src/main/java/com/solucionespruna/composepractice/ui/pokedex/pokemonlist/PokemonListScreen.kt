package com.solucionespruna.composepractice.ui.pokedex.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.solucionespruna.composepractice.ComposePracticeApp
import com.solucionespruna.composepractice.R

@Composable
fun PokemonListScreen(
  viewModel: PokemonListViewModel = viewModel(),
  onClick: (Int) -> Unit
) {
  LaunchedEffect(key1 = Unit) {
    viewModel.getPokemonList()
  }
  val pokemonList by viewModel.pokemonList.collectAsState()

  PokemonListLayout(pokemonList = pokemonList, onClick)
}

@Composable
fun PokemonListLayout(pokemonList: List<PokemonViewModelData>, onClick: (Int) -> Unit, modifier: Modifier = Modifier) {
  Box(Modifier.safeContentPadding()) {
    LazyVerticalGrid(
      columns = GridCells.Fixed(2),
      modifier = modifier,
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(pokemonList.count()) {
        PokemonCard(pokemonViewModelData = pokemonList[it], color = MaterialTheme.colorScheme.error, onClick)
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PokemonListLayoutPreview() {
  val pokemonList = (1..10).map {
    PokemonViewModelData(
      id = it,
      name = "name $it"
    )
  }

  ComposePracticeApp {
    PokemonListLayout(pokemonList, onClick = {})
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonCard(
  pokemonViewModelData: PokemonViewModelData,
  color: Color,
  onClick: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  Card(onClick = { onClick(pokemonViewModelData.id) }, modifier = modifier) {
    Column(Modifier.background(color)) {
      Text(text = pokemonViewModelData.formattedId,
        Modifier
          .fillMaxWidth()
          .padding(top = 8.dp, end = 8.dp), textAlign = TextAlign.End)
      Text(
        text = pokemonViewModelData.name.capitalize(Locale.current),
        modifier = Modifier.padding(start = 12.dp),
        color = MaterialTheme.colorScheme.background,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold
      )
      BoxWithConstraints(Modifier.fillMaxWidth()) {
        val scope = this
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
            .width(scope.maxWidth * 0.6f)
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