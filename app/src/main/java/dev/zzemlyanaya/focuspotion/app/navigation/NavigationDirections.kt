package dev.zzemlyanaya.focuspotion.app.navigation

import dev.zzemlyanaya.focuspotion.app.data.PresetEntity

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

    fun presetEdit(preset: PresetEntity) = object : NavigationCommand() {
        override val destination = Destination.PresetEdit.route
        override val args = listOf(false, preset)
    }

    val presetNew = object : NavigationCommand() {
        override val destination = Destination.PresetEdit.route
        override val args = listOf(true)
    }

    fun editName(currentName: String) = object : NavigationCommand() {
        override val destination = Destination.PresetNameEdit.route
        override val args = listOf(currentName)
    }

    val pickIcon = object : NavigationCommand() {
        override val destination = Destination.IconPicker.route
    }

    fun pickNumber(max: Int) = object : NavigationCommand() {
        override val destination = Destination.NumberPicker.route
        override val args = listOf(max)
    }

    fun timer(preset: PresetEntity) = object : NavigationCommand() {
        override val destination = Destination.Timer.route
        override val args: List<PresetEntity> = listOf(preset)
    }
}