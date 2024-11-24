package dev.zzemlyanaya.focuspotion.features.pomodoro.impl.mapping

import dev.zzemlyanaya.focuspotion.features.pomodoro.api.model.*
import dev.zzemlyanaya.focuspotion.features.pomodoro.impl.model.TimerContract
import javax.inject.Inject

class TimerUiModelMapper @Inject constructor() {

    fun map(timer: TimerEntity) = TimerContract.UiState(
        stageName = timer.currentType.stage,
        timer = getTimeText(timer.timeLeftMills),
        progress = timer.currentProgress,
        sessionsCount = timer.sessions,
        currentSession = timer.currentSession,
        isLongBreak = timer.currentType == TimerType.LONG_BREAK,
        isStarted = timer.isStarted,
        isPaused = timer.isPaused,
        isFinished = timer.isFinished,
    )

}