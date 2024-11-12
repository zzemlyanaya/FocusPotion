package dev.zzemlyanaya.focuspotion.features.presets.model

import kotlinx.serialization.Serializable

@Serializable
class NumberPickerArgs(
    val current: Int,
    val max: Int
)