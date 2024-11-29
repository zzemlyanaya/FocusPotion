package dev.zzemlyanaya.focuspotion.features.pomodoro.api.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import dev.zzemlyanaya.focuspotion.features.pomodoro.api.model.*
import dev.zzemlyanaya.focuspotion.features.presets.api.model.PresetEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimerRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private val mutableState = MutableStateFlow(DEFAULT_TIMER)

    val timerState: Flow<TimerEntity> = mutableState.asStateFlow()
    val isTimerActive: Flow<Boolean> = dataStore.data.map { it[ACTIVE_TIMER_KEY] ?: false }

    @Volatile
    private var isPaused = false


    suspend fun setIsTimerActive(isActive: Boolean) = dataStore.edit { it[ACTIVE_TIMER_KEY] = isActive }

    fun reset(presetEntity: PresetEntity) {
        mutableState.value = presetEntity.toDataState()
    }

    suspend fun start() {
        if (isPaused.not()) {
            mutableState.value = mutableState.value.copy(isStarted = true, currentType = TimerType.FOCUS)
            setIsTimerActive(true)
        }

        resume()
    }

    suspend fun resume() {
        isPaused = false

        flow {
            while (isPaused.not()) {
                emit(mutableState.value.copy(isPaused = false))
                delay(16)
            }
        }.map { timer ->
            val timeLeftMills = timer.timeLeftMills - 16
            if (timeLeftMills <= 0) {
                if (timer.currentIteration == 0 && timer.repeatAfterLongBreak.not()) {
                    stop()
                    return@map timer.copy(isFinished = true)
                }

                createNewIteration(timer)
            } else {
                timer.copy(timeLeftMills = timeLeftMills)
            }
        }.collect { state ->
            mutableState.value = state
        }
    }

    fun pause() {
        isPaused = true
        mutableState.value = mutableState.value.copy(isPaused = true)
    }

    suspend fun stop() {
        isPaused = true
        setIsTimerActive(false)
    }

    private fun createNewIteration(timer: TimerEntity): TimerEntity {
        val nextType = when (timer.currentType) {
            TimerType.FOCUS -> if (timer.repeatAfterLongBreak) TimerType.LONG_BREAK else TimerType.BREAK
            TimerType.BREAK -> TimerType.FOCUS
            TimerType.LONG_BREAK -> TimerType.FOCUS
            TimerType.NONE -> TimerType.FOCUS
        }

        val currentSession = when {
            timer.currentType == TimerType.LONG_BREAK -> 1
            nextType == TimerType.FOCUS -> timer.currentSession + 1
            else -> timer.currentSession
        }

        val nextIterationMills = TimeUnit.SECONDS.toMillis(getIterationSeconds(timer, nextType).toLong())

        return timer.copy(
            currentType = nextType,
            currentIteration = timer.currentIteration - 1,
            currentSession = currentSession,
            currentIterationMills = nextIterationMills,
            timeLeftMills = nextIterationMills,
        )
    }

    private fun getIterationSeconds(timer: TimerEntity, type: TimerType) = when(type) {
        TimerType.FOCUS -> timer.focusSeconds
        TimerType.BREAK -> timer.shortBreakSeconds
        TimerType.LONG_BREAK -> timer.longBreakSecond
        else -> 0
    }

    companion object {
        private val ACTIVE_TIMER_KEY = booleanPreferencesKey("active_timer")
    }
}