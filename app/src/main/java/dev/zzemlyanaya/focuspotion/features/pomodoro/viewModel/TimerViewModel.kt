package dev.zzemlyanaya.focuspotion.features.pomodoro.viewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zzemlyanaya.focuspotion.app.navigation.NavigationRouter
import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent
import dev.zzemlyanaya.focuspotion.core.viewModel.BaseViewModel
import dev.zzemlyanaya.focuspotion.features.pomodoro.model.TimerContract
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val router: NavigationRouter
) : BaseViewModel<TimerContract.UiState, TimerContract.Intent>(router) {

    override fun getInitialState() = TimerContract.UiState()

    override fun handleIntent(intent: BaseIntent) {
        when (intent) {
            is TimerContract.Intent.StartClick -> {}
            is TimerContract.Intent.PauseClick -> {}
            is TimerContract.Intent.StopClick -> {}
            else -> super.handleIntent(intent)
        }
    }
}