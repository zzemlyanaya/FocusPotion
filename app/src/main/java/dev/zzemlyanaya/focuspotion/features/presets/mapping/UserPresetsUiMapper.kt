package dev.zzemlyanaya.focuspotion.features.presets.mapping

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import dev.zzemlyanaya.focuspotion.app.data.PresetEntity
import dev.zzemlyanaya.focuspotion.features.presets.model.PresetUiModel
import javax.inject.Inject

class UserPresetsUiMapper @Inject constructor() {

    fun mapList(list: List<PresetEntity>): List<PresetUiModel> = list.map(::map)

    private fun map(entity: PresetEntity) =
        PresetUiModel(
            name = entity.name,
            icon = Icons.Filled.Favorite,
        )
}