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
import dev.zzemlyanaya.focuspotion.features.presets.model.PresetsListContract
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PresetsListViewModel @Inject constructor(
    presetsRepository: UserPresetsRepository,
    private val mapper: UserPresetsUiMapper,
    private val router: NavigationRouter
) : BaseViewModel<PresetsListContract.UiState, PresetsListContract.Intent>(router) {

    private val presets: MutableList<PresetEntity> = mutableListOf()

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
            is PresetsListContract.Intent.PresetClick -> openPreset(intent.name)
            is PresetsListContract.Intent.CreateNew -> router.navigateTo(MainDirections.presetNew)
            else -> super.handleIntent(intent)

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