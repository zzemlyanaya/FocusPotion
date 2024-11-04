package dev.zzemlyanaya.focuspotion.features.presets.model

import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent

sealed interface PresetsListContract {

    data class UiState(
        val presets: List<PresetUiModel> = emptyList(),
    ) {
        val isEmptyMessageVisible: Boolean = presets.isEmpty()
    }

    sealed interface Intent : BaseIntent {
        class PresetClick(val name: String) : Intent
        class PresetEditClick(val name: String) : Intent
        class PresetDeleteClick(val name: String) : Intent
        object UndoDeleteClick : Intent
        object CreateNew : Intent
    }
}