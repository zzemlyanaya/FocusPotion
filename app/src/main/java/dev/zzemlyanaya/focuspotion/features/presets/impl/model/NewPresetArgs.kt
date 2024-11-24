package dev.zzemlyanaya.focuspotion.features.presets.impl.model

import dev.zzemlyanaya.focuspotion.features.presets.api.model.PresetEntity
import kotlinx.serialization.Serializable

@Serializable
class NewPresetArgs(
    val isEditMode: Boolean = false,
    val editingId: Int = -1,
    val presetEntity: PresetEntity? = null
)