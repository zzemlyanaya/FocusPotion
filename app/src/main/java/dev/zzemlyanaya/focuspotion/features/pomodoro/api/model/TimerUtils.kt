package dev.zzemlyanaya.focuspotion.features.pomodoro.api.model

import dev.zzemlyanaya.focuspotion.features.presets.api.model.PresetEntity
import java.util.concurrent.TimeUnit
import kotlin.time.DurationUnit
import kotlin.time.toDuration


fun PresetEntity.toDataState() = TimerEntity(
    // Preset data
    sessions = this.sessions,
    focusSeconds = TimeUnit.MINUTES.toSeconds(this.focusTime.toLong()),
    shortBreakSeconds = TimeUnit.MINUTES.toSeconds(this.shortBreakTime.toLong()),
    longBreakSecond = TimeUnit.MINUTES.toSeconds(this.longBreakTime.toLong()),
    repeatAfterLongBreak = this.repeatAfterLongBreak,

    // Timer data
    currentType = TimerType.NONE,
    currentSession = 1,
    currentIteration = this.sessions * 2 - 1,
    currentIterationMills = TimeUnit.MINUTES.toMillis(this.focusTime.toLong()),
    timeLeftMills = TimeUnit.MINUTES.toMillis(this.focusTime.toLong()),
    isStarted = false,
    isPaused = false,
    isFinished = false,
)

fun TimerEntity.toNotification() = "${this.currentType.stage} • ${getTimeText(this.timeLeftMills)}"

fun getTimeText(mills: Long): String {
    val duration = mills.toDuration(DurationUnit.MILLISECONDS)
    val mins = duration.inWholeMinutes
    val secs = duration.minus(mins.toDuration(DurationUnit.MINUTES)).inWholeSeconds
    return "${if (mins < 10) 0 else ""}$mins:${if (secs < 10) 0 else ""}$secs"
}

val DEFAULT_TIMER = TimerEntity(
    sessions = 0,
    focusSeconds = 0,
    shortBreakSeconds = 0,
    longBreakSecond = 0,
    repeatAfterLongBreak = false,
    currentType = TimerType.NONE,
    currentSession = 0,
    currentIteration = 0,
    currentIterationMills = 0,
    timeLeftMills = 0,
    isStarted = false,
    isPaused = false,
    isFinished = false,
)