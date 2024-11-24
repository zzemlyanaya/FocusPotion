package dev.zzemlyanaya.focuspotion.features.pomodoro.api.service

interface TimerListener {
    fun start()
    fun pause()
    fun stop()
}