package dev.zzemlyanaya.focuspotion.app.navigation


sealed class Destination(val route: String) {
    object Main : Destination("main_route")
    object PresetsList : Destination("presets_list_route")
    object PresetEdit : Destination("presets_edit_route")
    object PresetNameEdit : Destination("presets_edit_name_route")
    object IconPicker : Destination("icon_picker_route")
    object NumberPicker : Destination("number_picker_route")
    object Timer : Destination("timer_route")
}