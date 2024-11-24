package dev.zzemlyanaya.focuspotion.features.presets.impl.model

import androidx.compose.ui.graphics.vector.ImageVector
import dev.zzemlyanaya.focuspotion.uikit.icons.AppIcons
import dev.zzemlyanaya.focuspotion.uikit.icons.More

class PresetUiModel(
    val name: String,
    val icon: ImageVector,
    val isSecondary: Boolean = false
) {

    companion object {
        val More = PresetUiModel(
            AppIcons.More.name,
            AppIcons.More,
            isSecondary = true
        )
    }
}