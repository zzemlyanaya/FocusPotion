package dev.zzemlyanaya.focuspotion.uikit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.wear.compose.material.MaterialTheme
import dev.zzemlyanaya.focuspotion.uikit.tokens.*

@Composable
fun FocusPotionTheme(
    spacing: Spacing = Spacing,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalSpacing provides spacing) {
        MaterialTheme(
            colors = potionColorPalette,
            content = content
        )
    }
}