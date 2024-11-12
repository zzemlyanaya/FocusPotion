package dev.zzemlyanaya.focuspotion.features.presets.model.contract

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import dev.zzemlyanaya.focuspotion.R
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.features.presets.PresetDefaults
import dev.zzemlyanaya.focuspotion.uikit.icons.AppIcons
import dev.zzemlyanaya.focuspotion.uikit.icons.Potion

sealed interface NewPresetContract {

    data class UiState(
        @StringRes val title: Int = R.string.edit_potion,
        val name: String = PresetDefaults.NAME,
        val icon: ImageVector = AppIcons.Potion,
        val focusTime: Int = PresetDefaults.FOCUS,
        val shortBreak: Int = PresetDefaults.SHORT_BREAK,
        val sessions: String = PresetDefaults.SESSIONS.toString(),
        val repeatAfterLongBreak: Boolean = false,
        val longBreak: Int = PresetDefaults.LONG_BREAK
    )

    sealed interface Intent : BaseIntent {
        object IconClick : Intent
        object FocusClick : Intent
        object ShortBreakClick : Intent
        object LongBreakClick : Intent
        object SessionsClick : Intent

        object CancelClick : Intent
        object SavePresetClick : Intent

        class RepeatSwitch(val isOn: Boolean) : Intent
        class NameEdit(val newName: String) : Intent
    }
}