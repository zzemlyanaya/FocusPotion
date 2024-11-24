package dev.zzemlyanaya.focuspotion.app.navigation

import dev.zzemlyanaya.focuspotion.features.pomodoro.impl.model.TimerArgs
import dev.zzemlyanaya.focuspotion.features.presets.api.model.PresetEntity
import dev.zzemlyanaya.focuspotion.features.presets.impl.model.*

object MainDirections {

    val default = object : NavigationCommand() {
        override val destination = ""
    }

    val back = object : NavigationCommand() {
        override val destination = "back"
    }

    val main  = object : NavigationCommand() {
        override val destination = Destination.Main.route
    }

    val presetsList = object : NavigationCommand() {
        override val destination = Destination.PresetsList.route
    }

    fun presetEdit(preset: PresetEntity, presetId: Int) = object : NavigationCommand() {
        override val destination = Destination.PresetEdit.route
        override val args = listOf(NewPresetArgs(isEditMode = true, presetId, preset))
    }

    val presetNew = object : NavigationCommand() {
        override val destination = Destination.PresetEdit.route
        override val args = listOf(NewPresetArgs())
    }

    fun pickIcon(current: Int) = object : NavigationCommand() {
        override val destination = Destination.IconPicker.route
        override val args = listOf(IconPickerArgs(current))
    }

    fun pickNumber(args: NumberPickerArgs) = object : NavigationCommand() {
        override val destination = Destination.NumberPicker.route
        override val args = listOf(args)
    }

    fun timer(preset: PresetEntity) = object : NavigationCommand() {
        override val destination = Destination.Timer.route
        override val args = listOf(TimerArgs(preset))
    }
}