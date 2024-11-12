package dev.zzemlyanaya.focuspotion.features.presets.model

import dev.zzemlyanaya.focuspotion.app.data.PresetEntity
import kotlinx.serialization.Serializable

@Serializable
class NewPresetArgs(
    val isEditMode: Boolean = false,
    val editingId: Int = -1,
    val presetEntity: PresetEntity? = null
)