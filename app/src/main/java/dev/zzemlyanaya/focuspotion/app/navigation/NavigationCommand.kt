package dev.zzemlyanaya.focuspotion.app.navigation

import kotlinx.serialization.Serializable


abstract class NavigationCommand {
    open val args: List<@Serializable Any> = emptyList()
    abstract val destination: String
}