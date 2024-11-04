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

    val presetEdit = object : NavigationCommand() {
        override val destination = Destination.PresetEdit.route
        override val args = listOf(false)
    }

    val presetNew = object : NavigationCommand() {
        override val destination = Destination.PresetEdit.route
        override val args = listOf(true)
    }

    fun timer(preset: PresetEntity) = object : NavigationCommand() {
        override val destination = Destination.Timer.route
        override val args: List<PresetEntity> = listOf(preset)
    }
}