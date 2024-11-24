package dev.zzemlyanaya.focuspotion.features.pomodoro.impl.model

import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent

sealed interface TimerContract {

    data class UiState(
        val stageName: String = "",
        val timer: String = "",
        val progress: Float = 0f,
        val sessionsCount: Int = 0,
        val currentSession: Int = 0,
        val isLongBreak: Boolean = false,
        val isStarted: Boolean = false,
        val isPaused: Boolean = false,
        val isFinished: Boolean = false,
    )

    sealed interface Intent : BaseIntent {
        object StartClick : Intent
        object PauseClick : Intent
        object StopClick : Intent
        object FinishedClick : Intent
    }
}