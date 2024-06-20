package com.solucionespruna.composepractice.ui.coffeshop.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.solucionespruna.composepractice.R
import com.solucionespruna.composepractice.ui.coffeshop.common.IconAccentButton
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme
import java.lang.Math.random
import java.util.Locale

@Composable
fun CoffeeList(modifier: Modifier = Modifier, onCoffeeClicked: (Int) -> Unit) {
  val coffees = (1..10).map {
    CoffeeItem(
      "https://loremflickr.com/320/320/coffee?ramdom$it",
      (random() * 10 % 4 + 1).toFloat(),
      "Caffe Mocha",
      "Deep Foam", (random() * 10).toFloat()
    )
  }

  LazyVerticalGrid(
    modifier = modifier.padding(top = 8.dp),
    columns = GridCells.Fixed(2),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    items(coffees.size) { coffeeIndex ->
      CoffeeItem(coffeeItem = coffees[coffeeIndex]) {
        onCoffeeClicked(coffeeIndex)
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun CoffeeListPreview() {
  ComposePracticeTheme {
    CoffeeList {}
  }
}

@Composable
fun CoffeeItem(coffeeItem: CoffeeItem, modifier: Modifier = Modifier, onClick: () -> Unit) {
  Card(
    modifier = modifier
      .clickable { onClick() },
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    shape = RoundedCornerShape(16.dp)

  ) {
    Column(
      Modifier
        .background(Color.White)
        .padding(8.dp)) {
      Box {
        AsyncImage(
          model = ImageRequest.Builder(LocalContext.current)
            .data(coffeeItem.imageURL)
            .build(),
          contentDescription = null,
          imageLoader = ImageLoader(LocalContext.current),
          modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth(),
          placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
          contentScale = ContentScale.Crop,
        )
        Row(
          Modifier
            .padding(
              vertical = 8.dp,
              horizontal = 16.dp
            )
            .align(Alignment.TopEnd),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Icon(
            modifier = Modifier.size(12.dp),
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = Color.Yellow
          )
          Text(
            text = String.format(Locale.US, "%.1f", coffeeItem.rating),
            style = MaterialTheme.typography.labelMedium,
            color = Color.Yellow
          )
        }
      }
      Text(text = coffeeItem.name, Modifier.padding(top = 8.dp), style = MaterialTheme.typography.titleMedium)
      Text(
        text = coffeeItem.description,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.tertiary
      )
      Row(
        Modifier
          .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "$ ${String.format(Locale.US, "%.2f", coffeeItem.price)}",
          style = MaterialTheme.typography.titleMedium)
        IconAccentButton(icon = Icons.Default.Add, padding = 0f)
      }
    }
  }
}

//@Preview
@Composable
private fun CoffeeItemPreview() {
  ComposePracticeTheme {
    CoffeeItem(CoffeeItem(
      "https://loremflickr.com/320/320/coffee",
      4.8f,
      "Caffe Mocha",
      "Deep Foam",
      4.53f
    )) {}
  }
}