package dev.zzemlyanaya.focuspotion.features.mainScreen.model

import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.features.presets.model.PresetUiModel

sealed interface MainScreenContract {

    data class UiState(
        val firstRow: List<PresetUiModel> = emptyList(),
        val secondRow: List<PresetUiModel> = emptyList(),
    ) {
        val isEmptyMessageVisible: Boolean = firstRow.isEmpty()
    }

    sealed interface Intent : BaseIntent {
        class PresetClick(val name: String) : Intent
        object CreateNew : Intent
    }
}