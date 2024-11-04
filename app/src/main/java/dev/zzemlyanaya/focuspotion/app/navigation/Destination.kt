package dev.zzemlyanaya.focuspotion.app.navigation


sealed class Destination(val route: String) {
    data object Main : Destination("main_route")
    data object PresetsList : Destination("presets_list_route")
    data object PresetEdit : Destination("presets_list_route")
    data object Timer : Destination("timer_route")
}