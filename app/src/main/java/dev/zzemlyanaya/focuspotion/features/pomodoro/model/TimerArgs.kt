package dev.zzemlyanaya.focuspotion.features.pomodoro.model

import dev.zzemlyanaya.focuspotion.app.data.PresetEntity
import kotlinx.serialization.Serializable

@Serializable
class TimerArgs(
    val preset: PresetEntity
)