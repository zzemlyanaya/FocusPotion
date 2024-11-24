package dev.zzemlyanaya.focuspotion.features.pomodoro.impl.viewModel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zzemlyanaya.focuspotion.app.navigation.NavigationRouter
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.contract.ScreenUiState
import dev.zzemlyanaya.focuspotion.core.viewModel.BaseViewModel
import dev.zzemlyanaya.focuspotion.features.pomodoro.api.data.TimerRepository
import dev.zzemlyanaya.focuspotion.features.pomodoro.api.service.TimerListener
import dev.zzemlyanaya.focuspotion.features.pomodoro.impl.mapping.TimerUiModelMapper
import dev.zzemlyanaya.focuspotion.features.pomodoro.impl.model.TimerArgs
import dev.zzemlyanaya.focuspotion.features.pomodoro.impl.model.TimerContract
import dev.zzemlyanaya.focuspotion.features.presets.api.model.PresetEntity
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val repository: TimerRepository,
    private val mapper: TimerUiModelMapper,
    private val router: NavigationRouter
) : BaseViewModel<TimerContract.UiState, TimerContract.Intent>(router) {

    private var preset: PresetEntity? = null
    var timerListener: TimerListener? = null

    override val screenState = repository.timerState
        .map { ScreenUiState.Data(mapper.map(it)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ScreenUiState.Data(TimerContract.UiState())
        )

    init {
        router.getCurrentArgs<TimerArgs>()?.let {
            preset = it.preset
            repository.reset(it.preset)
        }
    }

    override fun getInitialState() = TimerContract.UiState()

    override fun handleIntent(intent: BaseIntent) {
        when (intent) {
            is TimerContract.Intent.StartClick -> timerListener?.start()
            is TimerContract.Intent.PauseClick -> timerListener?.pause()
            is TimerContract.Intent.StopClick -> {
                timerListener?.stop()
                preset?.let { repository.reset(it) }
            }
            is TimerContract.Intent.FinishedClick -> router.back()
            else -> super.handleIntent(intent)
        }
    }
}