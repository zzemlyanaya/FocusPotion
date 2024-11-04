package dev.zzemlyanaya.focuspotion.uikit.components

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text

@Composable
fun ScreenTitle(text: String) {
    Text(
        style = MaterialTheme.typography.button,
        color = MaterialTheme.colors.onSurfaceVariant,
        text = text
    )
}