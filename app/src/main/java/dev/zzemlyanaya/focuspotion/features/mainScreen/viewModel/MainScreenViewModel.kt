package dev.zzemlyanaya.focuspotion.features.mainScreen.viewModel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zzemlyanaya.focuspotion.app.navigation.MainDirections
import dev.zzemlyanaya.focuspotion.app.navigation.NavigationRouter
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.contract.ScreenUiState
import dev.zzemlyanaya.focuspotion.core.viewModel.BaseViewModel
import dev.zzemlyanaya.focuspotion.features.mainScreen.model.MainScreenContract
import dev.zzemlyanaya.focuspotion.features.presets.api.data.UserPresetsRepository
import dev.zzemlyanaya.focuspotion.features.presets.api.model.PresetEntity
import dev.zzemlyanaya.focuspotion.features.presets.impl.mapping.UserPresetsUiMapper
import dev.zzemlyanaya.focuspotion.features.presets.impl.model.PresetUiModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val presetsRepository: UserPresetsRepository,
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
            else -> {
                val data = presets.take(4)
                MainScreenContract.UiState(
                    firstRow = data.take(2),
                    secondRow = data.takeLast(2) +  PresetUiModel.More
                )
            }
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