package dev.zzemlyanaya.focuspotion.features.presets.impl.model

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
class NumberPickerArgs(
    @StringRes val title: Int,
    val current: Int,
    val max: Int
)