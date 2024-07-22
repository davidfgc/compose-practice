package com.solucionespruna.composepractice.ui.pokedex.pokemon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.solucionespruna.composepractice.ComposePracticeApp

@Composable
fun PokemonScreen(id: Int, modifier: Modifier = Modifier) {
  Box(modifier = modifier.safeContentPadding().fillMaxSize(), contentAlignment = Alignment.Center) {
    Text("pokemon id: $id")
  }
}

@Preview
@Composable
private fun PokemonScreenPreview() {
  ComposePracticeApp {
    PokemonScreen(1)
  }
}