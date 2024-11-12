package dev.zzemlyanaya.focuspotion.app.data

import kotlinx.serialization.Serializable

@Serializable
class PresetEntity(
    var name: String,
    var iconId: Int,
    var focusTime: Int,
    var shortBreakTime: Int,
    var sessions: Int,
    var repeatAfterLongBreak: Boolean,
    var longBreakTime: Int,
)