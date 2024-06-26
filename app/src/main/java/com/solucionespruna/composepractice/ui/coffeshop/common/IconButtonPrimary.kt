package com.solucionespruna.composepractice.ui.coffeshop.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IconButtonPrimary(icon: ImageVector, modifier: Modifier = Modifier, padding: Float) {
  IconButton(
    onClick = { /*TODO*/ },
    modifier
      .clip(RoundedCornerShape(16.dp))
      .background(MaterialTheme.colorScheme.primary)
      .padding(Dp(padding))
  ) {
    Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
  }
}