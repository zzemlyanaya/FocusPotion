package dev.zzemlyanaya.focuspotion.features.presets.model.contract

import androidx.annotation.StringRes
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent

sealed interface NumberPickerContract {

    data class UiState(
        @StringRes val title: Int = 0,
        val range: List<String> = emptyList(),
        val scrollToCurrent: Int? = null
    )

    sealed interface Intent : BaseIntent {
        object CancelClick : Intent
        class SaveClick(val current: Int) : Intent
    }
}