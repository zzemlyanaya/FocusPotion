package dev.zzemlyanaya.focuspotion.features.presets.impl.viewModel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zzemlyanaya.focuspotion.R
import dev.zzemlyanaya.focuspotion.app.navigation.MainDirections
import dev.zzemlyanaya.focuspotion.app.navigation.NavigationRouter
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.viewModel.BaseViewModel
import dev.zzemlyanaya.focuspotion.features.presets.api.data.UserPresetsRepository
import dev.zzemlyanaya.focuspotion.features.presets.api.model.PresetEntity
import dev.zzemlyanaya.focuspotion.features.presets.impl.PresetDefaults
import dev.zzemlyanaya.focuspotion.features.presets.impl.model.NewPresetArgs
import dev.zzemlyanaya.focuspotion.features.presets.impl.model.NumberPickerArgs
import dev.zzemlyanaya.focuspotion.features.presets.impl.model.contract.NewPresetContract
import dev.zzemlyanaya.focuspotion.features.presets.impl.viewModel.IconPickerViewModel.Companion.ICON_SELECTED_RESULT
import dev.zzemlyanaya.focuspotion.features.presets.impl.viewModel.NumberPickerViewModel.Companion.NUMBER_SELECTED_RESULT
import dev.zzemlyanaya.focuspotion.uikit.icons.AppIcons
import dev.zzemlyanaya.focuspotion.uikit.icons.all
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPresetViewModel @Inject constructor(
    private val repository: UserPresetsRepository,
    private val router: NavigationRouter
) : BaseViewModel<NewPresetContract.UiState, NewPresetContract.Intent>(router) {

    private var currentPreset: PresetEntity? = null
    private var isEditMode = false
    private var editingId = -1

    private val presets = repository.presets
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch { presets.collect() }

        val args = router.getCurrentArgs<NewPresetArgs>()
        isEditMode = args?.isEditMode ?: false
        editingId = args?.editingId ?: -1

        currentPreset = if (isEditMode) {
            args?.presetEntity
        } else {
            PresetEntity(
                name = PresetDefaults.NAME,
                iconId = 0,
                focusTime = PresetDefaults.FOCUS,
                shortBreakTime = PresetDefaults.SHORT_BREAK,
                longBreakTime = PresetDefaults.LONG_BREAK,
                sessions = PresetDefaults.SESSIONS,
                repeatAfterLongBreak = false
            )
        }

        currentPreset?.let { preset ->
            updateScreenState { it.copy(
                title = if (isEditMode) R.string.edit_potion else R.string.new_potion,
                name = preset.name,
                icon = AppIcons.all()[preset.iconId],
                focusTime = preset.focusTime,
                shortBreak = preset.shortBreakTime,
                longBreak = preset.longBreakTime,
                sessions = preset.sessions.toString(),
                repeatAfterLongBreak = preset.repeatAfterLongBreak
            ) }
        }
    }

    override fun getInitialState() = NewPresetContract.UiState()

    override fun handleIntent(intent: BaseIntent) {
        when(intent) {
            is NewPresetContract.Intent.IconClick -> onOpenIconPicker()
            is NewPresetContract.Intent.FocusClick -> onOpenFocusPicker()
            is NewPresetContract.Intent.ShortBreakClick -> onOpenShortBreakPicker()
            is NewPresetContract.Intent.LongBreakClick -> onOpenLongBreakPicker()
            is NewPresetContract.Intent.SessionsClick -> onOpenSessionsPicker()

            is NewPresetContract.Intent.CancelClick -> router.back()
            is NewPresetContract.Intent.SavePresetClick -> onSavePreset()

            is NewPresetContract.Intent.NameEdit -> handleName(intent.newName)
            is NewPresetContract.Intent.RepeatSwitch -> {
                currentPreset?.repeatAfterLongBreak = intent.isOn
                updateScreenState { it.copy(repeatAfterLongBreak = intent.isOn) }
            }
            else -> super.handleIntent(intent)
        }
    }

    private fun handleName(name: String) {
        val error = if (name.isEmpty()) {
            R.string.name_cant_be_empty
        } else if (presets.value.any { it.name == name }) {
            R.string.name_already_exists
        } else {
            null
        }

        error?.let { err ->
            updateScreenState { it.copy(nameError = err) }
            return
        }

        currentPreset?.name = name
        updateScreenState { it.copy(name = name, nameError = null) }
    }

    private fun onOpenIconPicker() {
        router.addResultListener<Int>(ICON_SELECTED_RESULT) { id ->
            currentPreset?.iconId = id
            updateScreenState { it.copy(icon = AppIcons.all()[id]) }
        }
        router.navigateTo(MainDirections.pickIcon(currentPreset?.iconId ?: 0))
    }

    private fun onOpenFocusPicker() {
        router.addResultListener<Int>(NUMBER_SELECTED_RESULT) { selected ->
            currentPreset?.focusTime = selected
            updateScreenState { it.copy(focusTime = selected) }
        }
        router.navigateTo(MainDirections.pickNumber(
            NumberPickerArgs(
                title = R.string.focus_for,
                max = PresetDefaults.MAX_INTERVAL,
                current = currentPreset?.focusTime ?: PresetDefaults.FOCUS
            )
        ))
    }

    private fun onOpenShortBreakPicker() {
        router.addResultListener<Int>(NUMBER_SELECTED_RESULT) { selected ->
            currentPreset?.shortBreakTime = selected
            updateScreenState { it.copy(shortBreak = selected) }
        }
        router.navigateTo(MainDirections.pickNumber(
            NumberPickerArgs(
                title = R.string.short_break,
                max = PresetDefaults.MAX_INTERVAL,
                current = currentPreset?.shortBreakTime ?: PresetDefaults.SHORT_BREAK
            )
        ))
    }

    private fun onOpenLongBreakPicker() {
        router.addResultListener<Int>(NUMBER_SELECTED_RESULT) { selected ->
            currentPreset?.longBreakTime = selected
            updateScreenState { it.copy(longBreak = selected) }
        }
        router.navigateTo(MainDirections.pickNumber(
            NumberPickerArgs(
                title = R.string.long_break,
                max = PresetDefaults.MAX_INTERVAL,
                current = currentPreset?.longBreakTime ?: PresetDefaults.LONG_BREAK
            )
        ))
    }

    private fun onOpenSessionsPicker() {
        router.addResultListener<Int>(NUMBER_SELECTED_RESULT) { selected ->
            currentPreset?.sessions = selected
            updateScreenState { it.copy(sessions = selected.toString()) }
        }
        router.navigateTo(MainDirections.pickNumber(
            NumberPickerArgs(
                title = R.string.sessions,
                max = PresetDefaults.MAX_SESSIONS,
                current = currentPreset?.sessions ?: PresetDefaults.SESSIONS
            )
        ))
    }

    private fun onSavePreset() {
        val preset = currentPreset ?: return
        if (isEditMode) presets.value[editingId].apply {
            name = preset.name
            iconId = preset.iconId
            focusTime = preset.focusTime
            shortBreakTime = preset.shortBreakTime
            longBreakTime = preset.longBreakTime
            sessions = preset.sessions
            repeatAfterLongBreak = preset.repeatAfterLongBreak
        }

        val forSave = if (isEditMode) presets.value else { presets.value + preset }

        ioScope.launch {
            repository.savePresets(forSave)
            router.back()
        }
    }

    override fun handleException(e: Throwable) {
        // TODO
    }
}