package dev.zzemlyanaya.focuspotion.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.zzemlyanaya.focuspotion.features.pomodoro.api.data.TimerRepository
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {
    @Inject
    lateinit var timerRepository: TimerRepository
}