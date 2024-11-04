package dev.zzemlyanaya.focuspotion.features.mainScreen.viewModel

import androidx.lifecycle.viewModelScope
import dev.zzemlyanaya.focuspotion.app.data.PresetEntity
import dev.zzemlyanaya.focuspotion.app.data.UserPresetsRepository
import dev.zzemlyanaya.focuspotion.app.navigation.MainDirections
import dev.zzemlyanaya.focuspotion.app.navigation.NavigationRouter
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.contract.ScreenUiState
import dev.zzemlyanaya.focuspotion.core.viewModel.BaseViewModel
import dev.zzemlyanaya.focuspotion.features.mainScreen.model.MainScreenContract
import dev.zzemlyanaya.focuspotion.features.presets.mapping.UserPresetsUiMapper
import dev.zzemlyanaya.focuspotion.features.presets.model.PresetUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    presetsRepository: UserPresetsRepository,
    private val mapper: UserPresetsUiMapper,
    private val router: NavigationRouter
) : BaseViewModel<MainScreenContract.UiState, MainScreenContract.Intent>(router) {

    private val presets: MutableList<PresetEntity> = mutableListOf()

    override val screenState = presetsRepository.presets
        .map {
            presets.clear()
            presets.addAll(it)

            ScreenUiState.Data(mapPresetsToUiState(mapper.mapList(it)))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ScreenUiState.Data(MainScreenContract.UiState())
        )

    override fun getInitialState() = MainScreenContract.UiState()

    override fun handleIntent(intent: BaseIntent) {
        when (intent) {
            is MainScreenContract.Intent.PresetClick -> openPreset(intent.name)
            is MainScreenContract.Intent.CreateNew -> router.navigateTo(MainDirections.presetNew)
            else -> super.handleIntent(intent)

        }
    }

    private fun mapPresetsToUiState(presets: List<PresetUiModel>): MainScreenContract.UiState =
        when (presets.size) {
            0 -> MainScreenContract.UiState()
            1, 2 -> MainScreenContract.UiState(firstRow = presets + PresetUiModel.More)
            3 -> MainScreenContract.UiState(firstRow = presets.dropLast(1) + PresetUiModel.More)
            else -> MainScreenContract.UiState(firstRow = presets.take(4) + PresetUiModel.More)
        }

    private fun openPreset(name: String) {
        if (name == PresetUiModel.More.name) {
            router.navigateTo(MainDirections.presetsList)
            return
        }

        val preset = presets.find { it.name == name } ?: return
        router.navigateTo(MainDirections.timer(preset))
    }
}