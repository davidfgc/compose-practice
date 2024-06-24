package com.solucionespruna.composepractice.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun NavigationIcon(onUpClick: () -> Unit, tint: Color = MaterialTheme.colorScheme.onPrimary) {
  IconButton(onClick = { onUpClick() }) {
    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, tint = tint)
  }
}