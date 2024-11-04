package dev.zzemlyanaya.focuspotion.app.data

import kotlinx.serialization.Serializable

@Serializable
class PresetEntity(
    val name: String,
    val iconId: Int,
    val focusTime: Int,
    val shortBreakTime: Int,
    val sessions: Int,
    val repeatAfterLongBreak: Boolean,
    val longBreakTime: Int,
)