package com.solucionespruna.composepractice.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
  primary = Brown,
  onPrimary = White,
  primaryContainer = LighterBrown,
  secondary = LightBrown,
  tertiary = Black,
  onTertiary = Gray,
  error = Red,
  onError = White,
  background = White,
  onBackground = Black,
  surface = LightGray,
  surfaceVariant = LightGray,
  onSurface = Black

  /* Other default colors to override
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ComposePracticeTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  ChangeStatusBarColor(isDark = darkTheme)
  MaterialTheme(
    colorScheme = LightColorScheme,
    typography = Typography,
    content = content
  )
}

@Composable
fun ChangeStatusBarColor(isDark: Boolean) {
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = Color.Transparent.toArgb()
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDark
    }
  }
}