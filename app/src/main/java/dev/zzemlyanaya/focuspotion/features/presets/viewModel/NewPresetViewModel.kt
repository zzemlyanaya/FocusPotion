package dev.zzemlyanaya.focuspotion.features.presets.viewModel

import dev.zzemlyanaya.focuspotion.app.data.PresetEntity
import dev.zzemlyanaya.focuspotion.app.navigation.MainDirections
import dev.zzemlyanaya.focuspotion.app.navigation.NavigationRouter
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.viewModel.BaseViewModel
import dev.zzemlyanaya.focuspotion.features.presets.PresetDefaults
import dev.zzemlyanaya.focuspotion.features.presets.model.NewPresetContract
import java.util.UUID
import javax.inject.Inject

class NewPresetViewModel @Inject constructor(
    private val router: NavigationRouter
) : BaseViewModel<NewPresetContract.UiState, NewPresetContract.Intent>(router) {

    private var currentPreset: PresetEntity? = null

    override fun getInitialState() = NewPresetContract.UiState()

    override fun handleIntent(intent: BaseIntent) {
        when(intent) {
            is NewPresetContract.Intent.NameClick -> changeNameClick()
            is NewPresetContract.Intent.IconClick -> {}
            is NewPresetContract.Intent.FocusClick -> {}
            is NewPresetContract.Intent.ShortBreakClick -> {}
            is NewPresetContract.Intent.LongBreakClick -> {}
            is NewPresetContract.Intent.SessionsClick -> {}

            is NewPresetContract.Intent.CancelClick -> {}
            is NewPresetContract.Intent.SavePresetClick -> {}

            is NewPresetContract.Intent.RepeatSwitch -> {
                currentPreset?.repeatAfterLongBreak = intent.isOn
                updateScreenState { it.copy(repeatAfterLongBreak = intent.isOn) }
            }
            else -> super.handleIntent(intent)
        }
    }

    private fun changeNameClick() {
        router.addResultListener<String>(NAME_EDIT_RESULT) {
            currentPreset?.name = it
        }
        router.navigateTo(MainDirections.editName(currentPreset?.name ?: PresetDefaults.NAME))
    }

    companion object {
        val NAME_EDIT_RESULT = UUID.randomUUID().toString()
    }
}