package dev.zzemlyanaya.focuspotion.features.presets.mapping

import dev.zzemlyanaya.focuspotion.app.data.PresetEntity
import dev.zzemlyanaya.focuspotion.features.presets.model.PresetUiModel
import dev.zzemlyanaya.focuspotion.uikit.icons.AppIcons
import dev.zzemlyanaya.focuspotion.uikit.icons.all
import javax.inject.Inject

class UserPresetsUiMapper @Inject constructor() {

    fun mapList(list: List<PresetEntity>): List<PresetUiModel> = list.map(::map)

    private fun map(entity: PresetEntity) =
        PresetUiModel(
            name = entity.name,
            icon = AppIcons.all()[entity.iconId],
        )
}