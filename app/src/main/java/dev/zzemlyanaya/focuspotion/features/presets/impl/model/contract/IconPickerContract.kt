package dev.zzemlyanaya.focuspotion.features.presets.impl.model.contract

import androidx.compose.ui.graphics.vector.ImageVector
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent

sealed interface IconPickerContract {

    data class UiState(
        val icons: List<ImageVector>,
        val scrollToCurrent: Int? = null
    )

    sealed interface Intent : BaseIntent {
        object CancelClick : NewPresetContract.Intent
        class SaveClick(val currentId: Int) : NewPresetContract.Intent
    }
}