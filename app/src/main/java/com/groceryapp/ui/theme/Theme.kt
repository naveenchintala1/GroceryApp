package com.groceryapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColorScheme(
    primary = orange,
    secondary = orange,
    background = white
)

@Composable
fun GroceryAppTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colorScheme = LightColorPalette,
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )

}