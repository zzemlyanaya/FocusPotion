package dev.zzemlyanaya.focuspotion.features.presets.viewModel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zzemlyanaya.focuspotion.app.data.PresetEntity
import dev.zzemlyanaya.focuspotion.app.data.UserPresetsRepository
import dev.zzemlyanaya.focuspotion.app.navigation.MainDirections
import dev.zzemlyanaya.focuspotion.app.navigation.NavigationRouter
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.contract.ScreenUiState
import dev.zzemlyanaya.focuspotion.core.viewModel.BaseViewModel
import dev.zzemlyanaya.focuspotion.features.presets.mapping.UserPresetsUiMapper
import dev.zzemlyanaya.focuspotion.features.presets.model.PresetUiModel
import dev.zzemlyanaya.focuspotion.features.presets.model.contract.PresetsListContract
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PresetsListViewModel @Inject constructor(
    private val presetsRepository: UserPresetsRepository,
    private val mapper: UserPresetsUiMapper,
    private val router: NavigationRouter
) : BaseViewModel<PresetsListContract.UiState, PresetsListContract.Intent>(router) {

    private val presets: MutableList<PresetEntity> = mutableListOf()
    private var deletedPreset: PresetEntity? = null
    private var deleteIndex = 0

    override val screenState = presetsRepository.presets
        .map {
            presets.clear()
            presets.addAll(it)

            ScreenUiState.Data(PresetsListContract.UiState(mapper.mapList(it)))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ScreenUiState.Data(PresetsListContract.UiState())
        )

    override fun getInitialState() = PresetsListContract.UiState()

    override fun handleIntent(intent: BaseIntent) {
        when (intent) {
            is PresetsListContract.Intent.PresetClick -> startPreset(intent.name)
            is PresetsListContract.Intent.CreateNew -> router.navigateTo(MainDirections.presetNew)
            is PresetsListContract.Intent.PresetEditClick -> editPreset(intent.name)
            is PresetsListContract.Intent.PresetDeleteClick -> deletePreset(intent.name)
            is PresetsListContract.Intent.UndoDeleteClick -> undoDelete()
            else -> super.handleIntent(intent)

        }
    }

    private fun startPreset(name: String) {
        if (name == PresetUiModel.More.name) {
            router.navigateTo(MainDirections.presetsList)
            return
        }

        val preset = presets.find { it.name == name } ?: return
        router.navigateTo(MainDirections.timer(preset))
    }

    private fun editPreset(name: String) {
        val presetId = presets.indexOfFirst { it.name == name }
        if (presetId == -1) return
        router.navigateTo(MainDirections.presetEdit(presets[presetId], presetId))
    }

    private fun deletePreset(name: String) {
        deleteIndex = presets.indexOfFirst { it.name == name }
        deletedPreset = presets.removeAt(deleteIndex)

        saveCurrentPresets()
    }

    private fun undoDelete() {
        deletedPreset?.let {
            presets.add(deleteIndex, it)
            saveCurrentPresets()
        }
    }

    private fun saveCurrentPresets() {
        ioScope.launch { presetsRepository.savePresets(presets) }
    }

    override fun handleException(e: Throwable) {
        // TODO
    }
}