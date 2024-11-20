package dev.zzemlyanaya.focuspotion.features.pomodoro.model

import dev.zzemlyanaya.focuspotion.core.contract.BaseIntent

sealed interface TimerContract {

    data class UiState(
        val stageName: String = "",
        val timer: String = "",
        val progress: Float = 0f,
        val sessionsCount: Int = 0,
        val currentSession: Int = 0,
        val isStarted: Boolean = false,
        val isPaused: Boolean = false,
    )

    sealed interface Intent : BaseIntent {
        object StartClick : Intent
        object PauseClick : Intent
        object StopClick : Intent
    }
}