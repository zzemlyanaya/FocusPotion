package dev.zzemlyanaya.focuspotion.features.pomodoro.impl.model

import dev.zzemlyanaya.focuspotion.features.presets.api.model.PresetEntity
import kotlinx.serialization.Serializable

@Serializable
class TimerArgs(
    val preset: PresetEntity
)